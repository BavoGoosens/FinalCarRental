package session;

import naming.NamingServiceRemote;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Team Loco on 27/10/2014.
 */
public class RunSessionManager {

    private static String namingHost = "localhost";
    private static int namingPort = 1234;
    private static String namingName = "awesome";

    private static String sessionHost = "localhost";
    private static int sessionPort = 1099;
    private static String sessionName = "SessionMeister";

    /**
     * The SessionManager needs to know where new companies are to be registered.
     * It also needs to be able to access the namingservice to lookup or register
     * new companies and such.
     * Please also provide the location details where the session manager runs.
     *
     * Thus please provide:
     *
     *      args[0] = naming service host
     *      args[1] = naming service host
     *      args[2] = naming service name
     *
     *      args[3] = host for the session manager
     *      args[4] = port for the session manager
     *      args [5] = name for the session manager
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        System.setSecurityManager(null);
        try{
            namingHost = args[0];
            namingPort = Integer.parseInt(args[1]);
            namingName = args[2];

            sessionHost = args[3];
            sessionPort = Integer.parseInt(args[4]);
            sessionName = args[5];
        } catch (Exception e){
            System.out.println("There was a problem with your program params " +
                    "\nWe will use the default params \nsession server host = " + sessionHost +
                    "\nsession server port = " + sessionPort + "\nsession server name = " + sessionName +
                    "\nnaming service host = " + namingHost + "\nnaming service port = " + namingPort +
                    "\nnaming service name = " + namingName);
        } finally {
            Registry reg = LocateRegistry.getRegistry(namingHost,namingPort);
            NamingServiceRemote namingService = (NamingServiceRemote) reg.lookup(namingName);

            SessionManagerRemote manager = new SessionManager(sessionHost,sessionPort,sessionName, namingService);
            SessionManagerRemote stub = (SessionManagerRemote) UnicastRemoteObject.exportObject(manager, 0);

            Registry registry = LocateRegistry.getRegistry(sessionHost,sessionPort);
            registry.rebind(sessionName, stub);
        }

    }

}
