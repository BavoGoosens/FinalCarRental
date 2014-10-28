package session;


import naming.NamingServiceRemote;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class SessionManager implements SessionManagerRemote {

    private NamingServiceRemote namingService;

    public  SessionManager(NamingServiceRemote namingService){
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
