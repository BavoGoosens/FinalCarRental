package session;


import naming.NamingServiceRemote;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class SessionManager implements SessionManagerRemote {

    private NamingServiceRemote namingService;
    private String host;
    private int port;
    private String name;

    public  SessionManager(String host, int port, String name, NamingServiceRemote namingService){
        this.host = host;
        this.port = port;
        this.name = name;

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
