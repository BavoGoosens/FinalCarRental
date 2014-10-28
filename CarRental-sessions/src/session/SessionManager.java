package session;


import naming.NamingServiceRemote;

import java.util.HashMap;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class SessionManager implements SessionManagerRemote {

    private NamingServiceRemote namingService;
    private String host;
    private int port;
    private String name;
    private HashMap<String, ManagerSessionRemote> activeManagerSessions;
    private HashMap<String, ReservationSessionRemote> activeReservationSessions;

    public SessionManager(String host, int port, String name, NamingServiceRemote namingService){
        this.host = host;
        this.port = port;
        this.name = name;
        this.namingService = namingService;
        this.activeManagerSessions = new HashMap<String, ManagerSessionRemote>();
        this.activeReservationSessions = new HashMap<String, ReservationSessionRemote>();
    }

    @Override
    public ManagerSessionRemote getManagerSessionRemote(String name) {
        if (!this.activeManagerSessions.containsKey(name)) {
            this.activeManagerSessions.put(name, new ManagerSession(this.namingService, name));
        }
        return this.activeManagerSessions.get(name);
    }

    @Override
    public ReservationSessionRemote getReservationSessionRemote(String name) {
        if (!this.activeReservationSessions.containsKey(name)) {
            this.activeReservationSessions.put(name, new ReservationSession(this.namingService, name));
        }
        return this.activeReservationSessions.get(name);
    }

    @Override
    public void closeManagerSession(ManagerSessionRemote session) {
        this.activeManagerSessions.remove(session.getClientName());
    }

    @Override
    public void closeReservationSession(ReservationSessionRemote session) {
        this.activeReservationSessions.remove(session.getClientName());
    }
}
