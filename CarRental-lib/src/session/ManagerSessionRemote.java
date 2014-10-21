package session;

import java.util.Collection;
import javax.ejb.Remote;
import rental.CarType;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */
@Remote
public interface ManagerSessionRemote {
    
    int getNumberOfReservationsBy(String clientName);
    
    int getNumberOfReservationsForCarType(String carRentalName, String carType);
    
    Collection<CarType> getCarTypesForCompany(String carRentalName);
    
}
