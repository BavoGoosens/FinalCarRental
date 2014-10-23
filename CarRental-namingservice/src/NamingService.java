import rental.ICarRentalCompany;
import session.NamingServiceRemote;

import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class NamingService implements NamingServiceRemote {

    private HashMap<String, ICarRentalCompany> registeredCompanies = new HashMap<>();

    @Override
    public void registerCompany(ICarRentalCompany company) throws RemoteException {
        //TODO

    }

    @Override
    public void unregisterCompany(String company) throws RemoteException {
        //TODO

    }

    @Override
    public ICarRentalCompany lookUpCompany(String company) throws RemoteException {
        //TODO
        return null;
    }
}
