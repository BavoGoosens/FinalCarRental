import rental.ICarRentalCompany;
import rental.IRentalServer;

import java.rmi.RemoteException;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class RentalServer implements IRentalServer {

    @Override
    public ICarRentalCompany createNewCompany() throws RemoteException {
        return null;
    }

}
