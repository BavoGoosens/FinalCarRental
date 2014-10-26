package session;

import Naming.DoubleNamingException;
import Naming.InvalidNamingException;
import Naming.NamingException;
import rental.ICarRentalCompany;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Team Loco on 22/10/2014.
 */
public interface NamingServiceRemote extends Remote{

    void registerCompany(ICarRentalCompany company) throws RemoteException, NamingException, DoubleNamingException;

    void unregisterCompany(String company) throws RemoteException;

    ICarRentalCompany lookUpCompany(String company) throws RemoteException, InvalidNamingException;

}
