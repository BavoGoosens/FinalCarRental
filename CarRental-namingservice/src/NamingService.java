import naming.DoubleNamingException;
import naming.InvalidNamingException;
import rental.ICarRentalCompany;
import naming.NamingServiceRemote;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class NamingService implements NamingServiceRemote {

    private String host;
    private int port;
    private String name;

    private HashMap<String, ICarRentalCompany> registeredCompanies = new HashMap<String, ICarRentalCompany>();

    public NamingService(String host, int port, String name){
        this.host = host;
        this.port = port;
        this.name = name;
    }

    @Override
    public void registerCompany(ICarRentalCompany company) throws RemoteException, DoubleNamingException {
        String companyName = company.getName();
        if (!registeredCompanies.containsKey(companyName))
            registeredCompanies.put(companyName, company);
        else
            throw new DoubleNamingException("There is already a company registered under this name");
    }

    @Override
    public void unregisterCompany(String company) throws RemoteException {
        if (registeredCompanies.containsKey(company))
            registeredCompanies.remove(company);
    }

    @Override
    public ICarRentalCompany lookUpCompany(String company) throws RemoteException, InvalidNamingException {
        if (registeredCompanies.containsKey(company))
            return registeredCompanies.get(company);
        else
            throw new InvalidNamingException("There is no company registered under that name");
    }

    @Override
    public Collection<ICarRentalCompany> getAllCompanies() {
        return this.registeredCompanies.values();
    }


}
