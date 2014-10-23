package session;

import rental.ICarRentalCompany;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Team Loco on 22/10/2014.
 */
public interface NamingServiceRemote extends Remote{

    void registerCompany(ICarRentalCompany company) throws RemoteException;

    void unregisterCompany(String company) throws RemoteException;

    ICarRentalCompany lookUpCompany(String company) throws RemoteException;

}
