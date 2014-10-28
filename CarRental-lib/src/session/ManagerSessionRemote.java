package session;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Set;

import rental.ICarRentalCompany;
import rental.CarType;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */

public interface ManagerSessionRemote extends Remote, Serializable {

    void registerCompany(ICarRentalCompany company) throws RemoteException;

    void unregisterCompany(String companyName) throws RemoteException;

    Collection<ICarRentalCompany> getAllCompanies() throws RemoteException;

    Collection<CarType> getAllCarTypes() throws RemoteException;

    int getNbOfReservations(String company, String cartype) throws RemoteException;

    Set<String> getBestClients() throws RemoteException;

    int getNbOfReservations(String client) throws RemoteException;

    CarType getMostPopularCarType(String company) throws RemoteException;
    
}
