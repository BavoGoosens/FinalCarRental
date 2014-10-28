package session;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import naming.InvalidNamingException;
import rental.*;

public interface ReservationSessionRemote extends Remote, Serializable {

    String getClientName();

    void createQuote(Date start, Date end, String carType, String carRentalName) throws ReservationException, RemoteException, InvalidNamingException;
    
    Collection<Quote> getQuotes() throws RemoteException;

    List<Reservation> confirmQuotes() throws ReservationException, RemoteException, InvalidNamingException;
    
    void getAvailableCarTypes(Date start, Date end) throws RemoteException;

    String getCheapestCarType(Date start, Date end) throws RemoteException;
    
}
