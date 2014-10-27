package rental;

import rental.ICarRentalCompany;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Team Loco on 23/10/2014.
 */
public interface IRentalServer extends Remote{

    ICarRentalCompany createNewCompany() throws RemoteException;

}
