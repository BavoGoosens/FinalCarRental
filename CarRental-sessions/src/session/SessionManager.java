package session;


import naming.NamingServiceRemote;
import rental.IRentalServer;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class SessionManager implements SessionManagerRemote {

    private IRentalServer rentalServer;
    private NamingServiceRemote namingService;

    public  SessionManager(IRentalServer rentalServer, NamingServiceRemote namingService){
        this.rentalServer = rentalServer;
        this.namingService = namingService;
    }

    @Override
    public ManagerSessionRemote getManagerSessionRemote(String name) {
        return null;
    }

    @Override
    public ReservationSessionRemote getReservationSessionRemote(String name) {
        return null;
    }
}
