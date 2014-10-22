package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import rental.ICarRentalCompany;
import rental.CarType;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */

public interface ManagerSessionRemote extends Remote {

    void registerCompany(String companyName, String csvData) throws RemoteException;

    void unregisterCompany(String companyName) throws RemoteException;

    Collection<ICarRentalCompany> getAllCompanies() throws RemoteException;

    Collection<CarType> getAllCarTypes() throws RemoteException;

    int getNbOfReservations(String company, String cartype) throws RemoteException;

    String getBestClient( String company) throws RemoteException;

    int getNbOfReservations(String client) throws RemoteException;

    CarType getMostPopularCarType(String company) throws RemoteException;
    
}
