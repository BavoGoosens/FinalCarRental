package session;

import java.rmi.RemoteException;
import java.util.*;

import rental.ICarRentalCompany;
import rental.CarType;
import rental.Quote;
import rental.ReservationException;


public class ReservationSession implements ReservationSessionRemote {


    @Override
    public void createQuote(String name, Date start, Date end, String carType, String carRentalName) throws ReservationException, RemoteException {

    }

    @Override
    public Collection<Quote> getQuotes() throws RemoteException {
        return null;
    }

    @Override
    public void confirmQuotes() throws ReservationException, RemoteException {

    }

    @Override
    public void getAvailableCarTypes(Date start, Date end) throws RemoteException {

    }

    @Override
    public CarType getCheapestCarType() throws RemoteException {
        return null;
    }
}
