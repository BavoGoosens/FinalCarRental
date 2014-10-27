package session;


import naming.NamingServiceRemote;
import rental.IRentalServer;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class SessionManager implements SessionManagerRemote {

    public  SessionManager(IRentalServer rentalServer, NamingServiceRemote namingService){

    }

    @Override
    public ManagerSession getManagerSessionRemote(String name) {
        return null;
    }

    @Override
    public ReservationSession getReservationSessionRemote(String name) {
        return null;
    }
}
