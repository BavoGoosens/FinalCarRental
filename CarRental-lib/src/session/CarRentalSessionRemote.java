package session;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import rental.Quote;
import rental.ReservationConstraints;
import rental.ReservationException;

@Remote
public interface CarRentalSessionRemote {

    Set<String> getAllRentalCompanies();
    
    void createQuote(String name, Date start, Date end, String carType, String carRentalName) throws ReservationException;
    
    List<Quote> getCurrentQuotes();
    
    void confirmQuotes(String name) throws ReservationException;
    
    void checkForAvailableCarTypes(Date start, Date end);
    
}
