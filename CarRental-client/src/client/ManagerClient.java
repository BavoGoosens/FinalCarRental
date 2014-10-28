package client;

import session.ManagerSessionRemote;
import session.SessionManagerRemote;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class ManagerClient {

    private ManagerSessionRemote session;

    private String companyName;

    private String filePath;

    public ManagerClient(String companyName, String filePath, String host, int port, String name) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        SessionManagerRemote sessionManager = (SessionManagerRemote) registry.lookup(name);
        session = sessionManager.getManagerSessionRemote("ManagerA");
        this.companyName = companyName;
        this.filePath = filePath;
    }

    public void run() throws IOException {
        String csvData = "";

        BufferedReader in = new BufferedReader(new FileReader(this.filePath));
        while (in.ready())
            csvData = in.readLine();

        session.registerCompany(companyName, csvData);
    }
}
