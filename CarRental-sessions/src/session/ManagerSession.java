package session;

import rental.ICarRentalCompany;
import rental.CarType;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */

public class ManagerSession implements ManagerSessionRemote {

    @Override
    public void registerCompany(String companyName, String csvData) throws RemoteException {

    }

    @Override
    public void unregisterCompany(String company) throws RemoteException {

    }

    @Override
    public Collection<ICarRentalCompany> getAllCompanies() throws RemoteException {
        return null;
    }

    @Override
    public Collection<CarType> getAllCarTypes() throws RemoteException {
        return null;
    }

    @Override
    public int getNbOfReservations(String company, String cartype) throws RemoteException {
        return 0;
    }

    @Override
    public Set<String> getBestClients() throws RemoteException {
        return null;
    }

    @Override
    public int getNbOfReservations(String client) throws RemoteException {
        return 0;
    }

    @Override
    public CarType getMostPopularCarType(String company) throws RemoteException {
        return null;
    }
}

