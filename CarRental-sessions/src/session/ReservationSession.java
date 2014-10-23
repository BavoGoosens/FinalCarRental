package session;

import java.rmi.RemoteException;
import java.util.*;

import rental.Quote;
import rental.Reservation;
import rental.ReservationException;


public class ReservationSession implements ReservationSessionRemote {


    @Override
    public String getClientName() {
        return null;
    }

    @Override
    public void createQuote(Date start, Date end, String carType, String carRentalName) throws ReservationException, RemoteException {

    }

    @Override
    public Collection<Quote> getQuotes() throws RemoteException {
        return null;
    }

    @Override
    public List<Reservation> confirmQuotes() throws ReservationException, RemoteException {
        return null;
    }

    @Override
    public void getAvailableCarTypes(Date start, Date end) throws RemoteException {

    }

    @Override
    public String getCheapestCarType(Date start, Date end) throws RemoteException {
        return null;
    }
}
