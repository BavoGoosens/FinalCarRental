package rental;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * Created by TeamLoco on 22/10/2014.
 */
public interface ICarRentalCompany extends Remote, Serializable {

    String getName();

    Quote createQuote(ReservationConstraints constraints, String guest) throws ReservationException;

    Reservation confirmQuote(Quote quote) throws ReservationException;

    void cancelReservation(Reservation reservation);

}
