package session;

import naming.DoubleNamingException;
import naming.InvalidNamingException;
import naming.NamingServiceRemote;
import naming.DoubleNamingException;
import rental.ICarRentalCompany;
import rental.CarType;

import java.rmi.RemoteException;
import java.util.*;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */

public class ManagerSession implements ManagerSessionRemote {

    private NamingServiceRemote namingService;
    private String clientName;

    public ManagerSession(NamingServiceRemote namingService, String clientName) {
        this.namingService = namingService;
        this.clientName = clientName;
    }

    @Override
    public String getClientName() {
        return this.clientName;
    }

    @Override
    public void registerCompany(ICarRentalCompany company) throws RemoteException, DoubleNamingException {
        this.namingService.registerCompany(company);
    }

    @Override
    public void unregisterCompany(String company) throws RemoteException {
        this.namingService.unregisterCompany(company);
    }

    @Override
    public Collection<ICarRentalCompany> getAllCompanies() throws RemoteException {
        return this.namingService.getAllCompanies();
    }

    @Override
    public Collection<CarType> getAllCarTypes() throws RemoteException {
        ArrayList<CarType> carTypes = new ArrayList<CarType>();
        for (ICarRentalCompany company: this.getAllCompanies()) {
            carTypes.addAll(company.getAllTypes());
        }
        return carTypes;
    }

    @Override
    public int getNbOfReservations(String companyName, String carType) throws RemoteException, InvalidNamingException {
        ICarRentalCompany company = this.namingService.lookUpCompany(companyName);
        return company.getNumberOfReservationsForCarType(carType);
    }

    @Override
    public Set<String> getBestClients() throws RemoteException {
        Set<String> bestClients = new TreeSet<String>();
        for (ICarRentalCompany company: this.getAllCompanies()) {
            bestClients.add(company.getBestClient());
        }
        return bestClients;
    }

    @Override
    public int getNbOfReservations(String client) throws RemoteException {
        int nbOfReservations = 0;
        for (ICarRentalCompany company: this.getAllCompanies()) {
            nbOfReservations += company.getNumberOfReservationsByClient(client);
        }
        return nbOfReservations;
    }

    @Override
    public CarType getMostPopularCarType(String company) throws RemoteException, InvalidNamingException {
        return this.namingService.lookUpCompany(company).getMostPopularCarType();
    }
}

