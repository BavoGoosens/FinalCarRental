package rental;

/**
 * Created by TeamLoco on 22/10/2014.
 */
public interface ICarRentalCompany {

    String getName();

    Quote createQuote(ReservationConstraints constraints, String guest) throws ReservationException;

    Reservation confirmQuote(Quote quote) throws ReservationException;

    void cancelReservation(Reservation reservation);

}
