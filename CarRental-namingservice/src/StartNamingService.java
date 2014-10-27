import Naming.NamingServiceRemote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Team Loco on 25/10/2014.
 */
public class StartNamingService {

    private static String host = "localhost";
    private static int port = 1234;
    private static String name = "awesome";

    /*
     The naming service needs to know on what address it needs to start
     Please provide these in the program args
     where
        args[0] = host location (if nothing is provided localhost will be used)
        args[1] = port number (if nothing is provided then default = 1234)
        args[2] = name (can be awesome, if nothing is provided then default = awesome)
    */
    public static void main(String[] args) throws RemoteException {
        try{
            host = args[0];
            port = Integer.parseInt(args[1]);
            name = args[3];
        } catch (Exception e) {
            System.out.println("There was a problem with your program params \nWe will use the default params\nhost = "
                    + host +"\nport = " + port + "\nname = " + name);
        } finally {
            NamingServiceRemote ns = new NamingService(host, port, name);
            NamingServiceRemote stub = (NamingServiceRemote) UnicastRemoteObject.exportObject(ns, 0);

            Registry registry = LocateRegistry.getRegistry(port);

            registry.rebind(name, stub);
        }
    }
}
