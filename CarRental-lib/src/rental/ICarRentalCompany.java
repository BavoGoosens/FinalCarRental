package rental;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

/**
 * Created by TeamLoco on 22/10/2014.
 */
public interface ICarRentalCompany extends Remote, Serializable {

    String getName();

    Quote createQuote(ReservationConstraints constraints, String guest) throws ReservationException;

    Reservation confirmQuote(Quote quote) throws ReservationException, RemoteException;

    void cancelReservation(Reservation reservation) throws RemoteException;

    Collection<CarType> getAllTypes() throws RemoteException;

    int getNumberOfReservationsForCarType(String carType) throws RemoteException;

    int getNumberOfReservationsByClient(String client) throws RemoteException;

    String getBestClient() throws RemoteException;

    CarType getMostPopularCarType() throws RemoteException;

    util.Tuple<CarType, Double> getCheapestCarType(Date start, Date end) throws RemoteException;
}
