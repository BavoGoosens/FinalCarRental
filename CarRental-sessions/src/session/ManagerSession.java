package session;

import rental.CarRentalCompany;
import rental.CarType;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */

public class ManagerSession implements ManagerSessionRemote {

    @Override
    public void registerCompany(CarRentalCompany company) throws RemoteException {

    }

    @Override
    public void unregisterCompany(String company) throws RemoteException {

    }

    @Override
    public Collection<CarRentalCompany> getAllCompanies() throws RemoteException {
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
    public String getBestClient(String company) throws RemoteException {
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

