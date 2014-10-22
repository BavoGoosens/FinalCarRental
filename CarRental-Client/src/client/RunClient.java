package client;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Team Loco on 21/10/2014.
 */
public class RunClient {

    public static void main(String[] args) throws Exception {
        System.setSecurityManager(null);
        ManagerClient manager1 = new ManagerClient("Dockx", "src/dockx.csv");
        ManagerClient manager2 = new ManagerClient("Hertz", "src/hertz");
        manager1.run();
        manager2.run();
        Client testClient = new Client("src/trips");
        testClient.run();

    }
}