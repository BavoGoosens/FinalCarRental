package session;

import naming.DoubleNamingException;
import naming.InvalidNamingException;
import rental.CarType;
import rental.ICarRentalCompany;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */

public interface ManagerSessionRemote extends Remote {

    String getClientName() throws RemoteException;

    void registerCompany(ICarRentalCompany company) throws RemoteException, DoubleNamingException;

    void unregisterCompany(String companyName) throws RemoteException;

    Collection<ICarRentalCompany> getAllCompanies() throws RemoteException;

    Collection<CarType> getAllCarTypes() throws RemoteException;

    int getNbOfReservations(String companyName, String carType) throws RemoteException, InvalidNamingException;

    Set<String> getBestClients() throws RemoteException;

    int getNbOfReservations(String client) throws RemoteException;

    CarType getMostPopularCarType(String company) throws RemoteException, InvalidNamingException;
}
