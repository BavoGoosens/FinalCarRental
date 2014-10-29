package session;


import naming.NamingServiceRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
        System.setSecurityManager(null);
        this.host = host;
        this.port = port;
        this.name = name;
        this.namingService = namingService;
        this.activeManagerSessions = new HashMap<String, ManagerSessionRemote>();
        this.activeReservationSessions = new HashMap<String, ReservationSessionRemote>();
    }

    @Override
    public ManagerSessionRemote getManagerSessionRemote(String name) throws RemoteException{
        if (!this.activeManagerSessions.containsKey(name)) {
            ManagerSessionRemote msr = new ManagerSession(this.namingService, name);
            ManagerSessionRemote stub = (ManagerSessionRemote) UnicastRemoteObject.exportObject(msr,0);
            //Registry reg = LocateRegistry.getRegistry(this.host,this.port);
            //reg.rebind(name, stub);
            this.activeManagerSessions.put(name, stub);
        }
        //return (ManagerSessionRemote) LocateRegistry.getRegistry(host,port).lookup(name);
        return this.activeManagerSessions.get(name);
    }

    @Override
    public ReservationSessionRemote getReservationSessionRemote(String name) throws RemoteException {
        if (!this.activeReservationSessions.containsKey(name)) {
            ReservationSessionRemote rsr = new ReservationSession(this.namingService, name);
            ReservationSessionRemote stub = (ReservationSessionRemote) UnicastRemoteObject.exportObject(rsr,0);
            this.activeReservationSessions.put(name, stub);
        }
        return this.activeReservationSessions.get(name);
    }

    @Override
    public void closeManagerSession(ManagerSessionRemote session) throws RemoteException {
        this.activeManagerSessions.remove(session.getClientName());
    }

    @Override
    public void closeReservationSession(ReservationSessionRemote session) throws RemoteException {
        this.activeReservationSessions.remove(session.getClientName());
    }
}
