package session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import rental.CarRentalCompany;
import rental.CarType;
import rental.Quote;
import rental.RentalStore;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationException;

@Stateful
public class ReservationSession implements CarRentalSessionRemote {
    
    private List<Quote> quotes = new ArrayList<Quote>();
    private List<Reservation> reservations = new ArrayList<Reservation>();

    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }
    
    @Override
    public void createQuote(String name, Date start, Date end, String carType, String carRentalName) throws ReservationException {
        ReservationConstraints constraints = new ReservationConstraints(start, end, carType);
        CarRentalCompany company = RentalStore.getRentals().get(carRentalName);
        this.quotes.add(company.createQuote(constraints, name));
    }

    @Override
    public List<Quote> getCurrentQuotes() {
        return this.quotes;
    }

    @Override
    public void confirmQuotes(String name) throws ReservationException {
        for (Quote quote: this.quotes) {
            if (quote.getCarRenter().equalsIgnoreCase(name)) {
                CarRentalCompany company = RentalStore.getRentals().get(quote.getRentalCompany());
                try {
                    this.reservations.add(company.confirmQuote(quote));
                } catch (ReservationException e) {
                    this.cancelAllReservations();
                    throw e;
                }
            }
        }
        this.quotes.clear();
    }
    
    private void cancelAllReservations() {
        for (Reservation reservation : this.reservations) {
            CarRentalCompany company = RentalStore.getRentals().get(reservation.getRentalCompany());
            company.cancelReservation(reservation);
        }
        this.reservations.clear();
    }

    @Override
    public void checkForAvailableCarTypes(Date start, Date end) {
        for (CarRentalCompany company : RentalStore.getRentals().values()) {
            Set<CarType> types = company.getAvailableCarTypes(start, end);
            System.out.println("Available car types for "+company.getName());
            for (CarType type : types) {
                System.out.println(type);
            }
        }
    }
    
    

    
    
}
