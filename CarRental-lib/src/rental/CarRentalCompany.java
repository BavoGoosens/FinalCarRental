package rental;

import util.Tuple;

import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarRentalCompany implements ICarRentalCompany {

	private static Logger logger = Logger.getLogger(CarRentalCompany.class.getName());
	
	private String name;
	private List<Car> cars;
	private Map<String, CarType> carTypes = new HashMap<String, CarType>();

	/***************
	 * CONSTRUCTOR *
	 ***************/

	public CarRentalCompany(String name, List<Car> cars) {
		logger.log(Level.INFO, "<{0}> rental.Car Rental Company {0} starting up...", name);
		setName(name);
		this.cars = cars;
		for(Car car:cars)
			carTypes.put(car.getType().getName(), car.getType());
	}

	/********
	 * NAME *
	 ********/

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	/*************
	 * CAR TYPES *
	 *************/

    @Override
	public Collection<CarType> getAllTypes(){
		return carTypes.values();
	}
	
	public CarType getType(String carTypeName){
		if(carTypes.containsKey(carTypeName))
			return carTypes.get(carTypeName);
		throw new IllegalArgumentException("<" + carTypeName + "> No cartype of name " + carTypeName);
	}
	
	public boolean isAvailable(String carTypeName, Date start, Date end) {
		logger.log(Level.INFO, "<{0}> Checking availability for car type {1}", new Object[]{name, carTypeName});
		return getAvailableCarTypes(start, end).contains(carTypes.get(carTypeName));
	}

    @Override
	public List<CarType> getAvailableCarTypes(Date start, Date end) {
		List<CarType> availableCarTypes = new ArrayList<CarType>();
		for (Car car : cars) {
			if (car.isAvailable(start, end)) {
				availableCarTypes.add(car.getType());
			}
		}
		return availableCarTypes;
	}
	
	/*********
	 * CARS *
	 *********/
	
	private Car getCar(int uid) {
		for (Car car : cars) {
			if (car.getId() == uid)
				return car;
		}
		throw new IllegalArgumentException("<" + name + "> No car with uid " + uid);
	}
	
	private List<Car> getAvailableCars(String carType, Date start, Date end) {
		List<Car> availableCars = new LinkedList<Car>();
		for (Car car : cars) {
			if (car.getType().getName().equals(carType) && car.isAvailable(start, end)) {
				availableCars.add(car);
			}
		}
		return availableCars;
	}

	/****************
	 * RESERVATIONS *
	 ****************/

	public Quote createQuote(ReservationConstraints constraints, String guest)
			throws ReservationException {
		logger.log(Level.INFO, "<{0}> Creating tentative reservation for {1} with constraints {2}", 
                        new Object[]{name, guest, constraints.toString()});
		
		CarType type = getType(constraints.getCarType());
		
		if(!isAvailable(constraints.getCarType(), constraints.getStartDate(), constraints.getEndDate()))
			throw new ReservationException("<" + name
				+ "> No cars available to satisfy the given constraints.");
		
		double price = calculateRentalPrice(type.getRentalPricePerDay(),constraints.getStartDate(), constraints.getEndDate());
		
		return new Quote(guest, constraints.getStartDate(), constraints.getEndDate(), getName(), constraints.getCarType(), price);
	}

	// Implementation can be subject to different pricing strategies
	private double calculateRentalPrice(double rentalPricePerDay, Date start, Date end) {
		return rentalPricePerDay * Math.ceil((end.getTime() - start.getTime())
						/ (1000 * 60 * 60 * 24D));
	}

	public synchronized Reservation confirmQuote(Quote quote) throws ReservationException {
		logger.log(Level.INFO, "<{0}> Reservation of {1}", new Object[]{name, quote.toString()});
		List<Car> availableCars = getAvailableCars(quote.getCarType(), quote.getStartDate(), quote.getEndDate());
		if(availableCars.isEmpty())
			throw new ReservationException("Reservation failed, all cars of type " + quote.getCarType()
	                + " are unavailable from " + quote.getStartDate() + " to " + quote.getEndDate());
		Car car = availableCars.get((int)(Math.random()*availableCars.size()));
		
		Reservation res = new Reservation(quote, car.getId());
		car.addReservation(res);
		return res;
	}

	public void cancelReservation(Reservation res) {
		logger.log(Level.INFO, "<{0}> Cancelling reservation {1}", new Object[]{name, res.toString()});
		getCar(res.getCarId()).removeReservation(res);
	}
        
    public List<Reservation> getAllReservations(String client) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        for ( Car car : this.cars){
            for (Reservation reservation : car.getReservations()){
                if (reservation.getCarRenter().equals(client))
                    reservations.add(reservation);
            }
        }
        return  reservations;
    }

    @Override
    public int getNumberOfReservationsForCarType(String carType) {
        int numberOfReservationsForCarType = 0;
        for (Car car : this.cars){
            if (car.getType().getName().equalsIgnoreCase(carType))
                numberOfReservationsForCarType += car.getReservations().size();
        }
        return numberOfReservationsForCarType;
    }

    @Override
    public int getNumberOfReservationsByClient(String client) {
        int numberOfReservationsByClient = 0;
        for (Car car: this.cars) {
            for (Reservation reservation: car.getReservations()) {
                if (client.equalsIgnoreCase(reservation.getCarRenter())) {
                    numberOfReservationsByClient++;
                }
            }
        }
        return numberOfReservationsByClient;
    }

    @Override
    public Collection<String> getAllClients() {
        ArrayList<String> clients = new ArrayList<String>();
        for (Car car: this.cars) {
            for (Reservation reservation: car.getReservations()) {
                if (!clients.contains(reservation.getCarRenter())) {
                    clients.add(reservation.getCarRenter());
                }
            }
        }
        return clients;
    }

    @Override
    public CarType getMostPopularCarType() {
        CarType mostPopularType = null;
        for (CarType type: this.getAllTypes()) {
            if (mostPopularType == null ||
                    this.getNumberOfReservationsForCarType(type.getName())
                            > this.getNumberOfReservationsForCarType(mostPopularType.getName())) {
                mostPopularType = type;
            }
        }
        return mostPopularType;
    }

    @Override
    public Tuple<CarType, Double> getCheapestCarType(Date start, Date end) {
        CarType cheapestCarType = null;
        for (CarType type: getAvailableCarTypes(start, end)) {
            if (cheapestCarType == null ||
                    this.calculateRentalPrice(type.getRentalPricePerDay(), start, end)
                            < this.calculateRentalPrice(cheapestCarType.getRentalPricePerDay(), start, end)) {
                cheapestCarType = type;
            }
        }
        return new Tuple<CarType, Double>(cheapestCarType, this.calculateRentalPrice(cheapestCarType.getRentalPricePerDay(), start, end));
    }
}