package session;

import naming.InvalidNamingException;
import naming.NamingServiceRemote;
import rental.*;
import util.Tuple;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class ReservationSession implements ReservationSessionRemote {

    private NamingServiceRemote namingService;
    private String clientName;
    private ArrayList<Quote> quotes;
    private ArrayList<Reservation> reservations;

    public ReservationSession(NamingServiceRemote namingService, String clientName) {
        this.namingService = namingService;
        this.clientName = clientName;
        this.quotes = new ArrayList<Quote>();
        this.reservations = new ArrayList<Reservation>();
    }

    private NamingServiceRemote getNamingService() {
        return this.namingService;
    }

    @Override
    public String getClientName() {
        return this.clientName;
    }

    @Override
    public void createQuote(Date start, Date end, String carType, String carRentalName) throws ReservationException, RemoteException, InvalidNamingException {
        ICarRentalCompany company = this.getNamingService().lookUpCompany(carRentalName);
        ReservationConstraints constraints = new ReservationConstraints(start, end, carType);
        this.quotes.add(company.createQuote(constraints, this.getClientName()));
    }

    @Override
    public Collection<Quote> getQuotes() throws RemoteException {
       return this.quotes;
    }

    @Override
    public List<Reservation> confirmQuotes() throws ReservationException, RemoteException, InvalidNamingException {
        for (Quote quote : this.quotes) {
            if (quote.getCarRenter().equalsIgnoreCase(this.getClientName())) {
                ICarRentalCompany company = this.getNamingService().lookUpCompany(quote.getRentalCompany());
                try {
                    this.reservations.add(company.confirmQuote(quote));
                } catch (ReservationException e) {
                    this.cancelAllReservations();
                    throw e;
                }
            }
        }
        this.quotes.clear();
        return this.reservations;
    }

    private void cancelAllReservations() throws InvalidNamingException, RemoteException {
        for (Reservation reservation : this.reservations) {
            ICarRentalCompany company = this.getNamingService().lookUpCompany(reservation.getRentalCompany());
            company.cancelReservation(reservation);
        }
    }


        @Override
    public void getAvailableCarTypes(Date start, Date end) throws RemoteException {

    }

    @Override
    public String getCheapestCarType(Date start, Date end) throws RemoteException {
        ArrayList<Tuple<CarType, Double>> cheapestCars = new ArrayList<Tuple<CarType, Double>>();
        for (ICarRentalCompany company: this.namingService.getAllCompanies()) {
            cheapestCars.add(company.getCheapestCarType(start, end));
        }
        Tuple<CarType, Double> cheapestCar = null;
        for (Tuple<CarType, Double> tuple: cheapestCars) {
            if (cheapestCar == null || tuple.getY() < cheapestCar.getY()) {
                cheapestCar = tuple;
            }
        }
        return cheapestCar.getX().getName();
    }
}
