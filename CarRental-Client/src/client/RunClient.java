package client;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Team Loco on 21/10/2014.
 */
public class RunClient {

    private static String host;

    private static int port;

    private static String name;

    /*
     The client needs to know the location of the SessionManager(Remote)
     since all interaction will go via these sessions.
     Also provide the name you chose to give the Manager.
     Please provide these in the program args
     where
        args[0] = host location
        args[1] = port number (if nothing is provided then default = 1099)
        args[2] = name (can be awesome)
    */
    public static void main(String[] args) throws Exception {
        // TODO: provide mechanism to catch undeclared server names
        System.setSecurityManager(null);
        try{
            host = args[0];
            try{
                port = Integer.parseInt(args[1]);
                name = args[2];
            }catch (NumberFormatException ex){
                System.out.println("Please provide a proper integer port address");
            }catch (Exception e){
                System.out.println("There was no port provided \n Port set to the default 1099");
                name = args[2];
            }
        } catch (Exception e){
            System.out.println("There was no host provided \nPlease provide host \nlike localhost perhaps");
        }

        System.setSecurityManager(null);

        ManagerClient manager1 = new ManagerClient("Dockx", "src/dockx.csv", host, port,name);
        ManagerClient manager2 = new ManagerClient("Hertz", "src/hertz", host , port, name);
        manager1.run();
        manager2.run();

        Client testClient = new Client("src/trips", host, port, name);
        testClient.run();

    }
}