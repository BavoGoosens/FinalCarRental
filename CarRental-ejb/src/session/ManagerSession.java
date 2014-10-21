package session;

import java.util.Collection;
import javax.ejb.Stateless;
import rental.CarRentalCompany;
import rental.CarType;
import rental.RentalStore;

/**
 *
 * @author Michiel Vandendriessche & Bavo Goosens
 */
@Stateless
public class ManagerSession implements ManagerSessionRemote {

    @Override
    public int getNumberOfReservationsBy(String clientName) {
        int reservations = 0;
        for (CarRentalCompany company : RentalStore.getRentals().values()) {
            reservations += company.getAllReservations(clientName).size();
        }
        return reservations;
    }

    @Override
    public int getNumberOfReservationsForCarType(String carRentalName, String carType) {
        CarRentalCompany company = RentalStore.getRentals().get(carRentalName);
        return company.getNumberOfReservationsForCarType(carType);
    }

    @Override
    public Collection<CarType> getCarTypesForCompany(String carRentalName) {
        CarRentalCompany company = RentalStore.getRentals().get(carRentalName);
        return company.getAllTypes();
    }
}
