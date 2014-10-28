package client;

/**
 * Created by Team Loco on 21/10/2014.
 */
public class RunClient {

    private static String host = "localhost";

    private static int port = 1099;

    private static String name = "SessionMeister";

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
        System.setSecurityManager(null);

        try{
            host = args[0];
            port = Integer.parseInt(args[1]);
            name = args[2];
        } catch (Exception e){
            System.out.println("There was a problem with your program arguments" +
                    "\nWe will use the following default values" + "\nhost = " +
                    host + "\nport = " + port + "\nname = " + name );
        }

        ManagerClient manager1 = new ManagerClient("Dockx", "src/dockx.csv", host, port,name);
        ManagerClient manager2 = new ManagerClient("Hertz", "src/hertz", host , port, name);
        manager1.run();
        manager2.run();

        Client testClient = new Client("src/trips", host, port, name);
        testClient.run();
    }
}