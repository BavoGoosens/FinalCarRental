package session;

import naming.NamingServiceRemote;
import rental.IRentalServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Team Loco on 27/10/2014.
 */
public class RunSessionManager {

    private static String companyHost;
    private static int companyPort;
    private static String companyName;

    private static String namingHost = "localhost";
    private static int namingPort = 1234;
    private static String namingName = "awesome";


    /**
     * The SessionManager needs to know where new companies are to be registered.
     * It also needs to be able to access the namingservice to lookup or register
     * new companies and such.
     * Please also provide the location details where the session manager runs.
     *
     * Thus please provide:
     *      args[0] = company server host
     *      args[1] = company server port
     *      args[2] = company server name
     *
     *      args[3] = naming service host
     *      args[4] = naming service host
     *      args[5] = naming service name
     *
     *      args[6] = host for the session manager
     *      args[7] = port for the session manager
     *      args [8] = name for the session manager
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        try{
            companyHost = args[0];
            companyPort = Integer.parseInt(args[1]);
            companyName = args[2];

            namingHost = args[3];
            namingPort = Integer.parseInt(args[4]);
            namingName = args[5];
        } catch (Exception e){
            System.out.println("There was a problem with your program params " +
                    "\nWe will use the default params \ncompany server host = " + companyHost +
                    "\ncompany server port = " + companyPort + "\ncompany server name = " + companyName +
                    "\nnaming service host = " + namingHost + "\nnaming service port = " + namingPort +
                    "\nnaming service name = " + namingName);
        } finally {
            Registry reg = LocateRegistry.getRegistry(companyHost,companyPort);
            IRentalServer rentalServer = (IRentalServer) reg.lookup(companyName);

            reg = LocateRegistry.getRegistry(namingHost,namingPort);
            NamingServiceRemote namingService = (NamingServiceRemote) reg.lookup(namingName);

            SessionManagerRemote manager = new SessionManager(rentalServer, namingService);

        }

    }

}
