package session;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Team Loco on 23/10/2014.
 */
public interface SessionManagerRemote extends Remote {

    // client name
    ManagerSessionRemote getManagerSessionRemote(String name) throws RemoteException, NotBoundException;

    ReservationSessionRemote getReservationSessionRemote(String name) throws RemoteException;

    void closeManagerSession(ManagerSessionRemote session) throws RemoteException;

    void closeReservationSession(ReservationSessionRemote session) throws RemoteException;
}
