package naming;

import rental.ICarRentalCompany;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Team Loco on 22/10/2014.
 */
public interface NamingServiceRemote extends Remote {

    void registerCompany(ICarRentalCompany company) throws RemoteException, DoubleNamingException;

    void unregisterCompany(String company) throws RemoteException;

    ICarRentalCompany lookUpCompany(String company) throws RemoteException, InvalidNamingException;

}
