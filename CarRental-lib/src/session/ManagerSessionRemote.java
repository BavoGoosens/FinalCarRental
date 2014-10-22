package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import rental.CarRentalCompany;
import rental.CarType;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */

public interface ManagerSessionRemote extends Remote {

    void registerCompany(CarRentalCompany company) throws RemoteException;

    void unregisterCompany(String company) throws RemoteException;

    Collection<CarRentalCompany> getAllCompanies() throws RemoteException;

    Collection<CarType> getAllCarTypes() throws RemoteException;

    int getNbOfReservations(String company, String cartype) throws RemoteException;

    String getBestClient( String company) throws RemoteException;

    int getNbOfReservations(String client) throws RemoteException;

    CarType getMostPopularCarType(String company) throws RemoteException;
    
}
