package rental;

import java.rmi.Remote;
import java.util.Collection;

/**
 * Created by TeamLoco on 22/10/2014.
 */
public interface ICarRentalCompany extends Remote {

    String getName();

    Quote createQuote(ReservationConstraints constraints, String guest) throws ReservationException;

    Reservation confirmQuote(Quote quote) throws ReservationException;

    void cancelReservation(Reservation reservation);

    Collection<CarType> getAllTypes();

    int getNumberOfReservationsForCarType(String carType);

    int getNumberOfReservationsByClient(String client);

    Collection<String> getAllClients();
}
