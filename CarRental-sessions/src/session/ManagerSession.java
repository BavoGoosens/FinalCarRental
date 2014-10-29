package session;

import naming.DoubleNamingException;
import naming.InvalidNamingException;
import naming.NamingServiceRemote;
import rental.CarType;
import rental.ICarRentalCompany;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */

public class ManagerSession implements ManagerSessionRemote {

    private NamingServiceRemote namingService;
    private String clientName;

    public ManagerSession(NamingServiceRemote namingService, String clientName) {
        System.setSecurityManager(null);
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
        Collection<String> allClients = this.getAllClients();
        Set<String> bestClients = new TreeSet<String>();
        String bestClient = null;
        for (String client: allClients) {
            if (bestClient == null) {
                bestClients.add(client);
            } else if (this.getNbOfReservations(client) > this.getNbOfReservations(bestClient)) {
                bestClients.remove(bestClient);
                bestClients.add(client);
            } else if (this.getNbOfReservations(client) == this.getNbOfReservations(bestClient)) {
                bestClients.add(client);
            }
        }
        return bestClients;
    }

    private Collection<String> getAllClients() throws RemoteException {
        ArrayList<String> clients = new ArrayList<String>();
        for (ICarRentalCompany company: this.getAllCompanies()) {
            for (String client: company.getAllClients()) {
                if (!clients.contains(client)) {
                    clients.add(client);
                }
            }
        }
        return clients;
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

