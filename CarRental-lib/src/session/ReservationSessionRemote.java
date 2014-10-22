package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import rental.*;

public interface ReservationSessionRemote extends Remote {


    void createQuote(String name, Date start, Date end, String carType, String carRentalName) throws ReservationException,RemoteException;
    
    Collection<Quote> getQuotes() throws RemoteException;
    
    void confirmQuotes() throws ReservationException, RemoteException;
    
    void getAvailableCarTypes(Date start, Date end) throws RemoteException;

    CarType getCheapestCarType() throws RemoteException;
    
}