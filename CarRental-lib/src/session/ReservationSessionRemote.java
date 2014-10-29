package session;

import naming.InvalidNamingException;
import rental.Quote;
import rental.Reservation;
import rental.ReservationException;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ReservationSessionRemote extends Remote {

    String getClientName() throws RemoteException;

    void createQuote(Date start, Date end, String carType, String carRentalName) throws ReservationException, RemoteException, InvalidNamingException;
    
    Collection<Quote> getQuotes() throws RemoteException;

    List<Reservation> confirmQuotes() throws ReservationException, RemoteException, InvalidNamingException;
    
    void getAvailableCarTypes(Date start, Date end) throws RemoteException;

    String getCheapestCarType(Date start, Date end) throws RemoteException;
    
}
