package client;

import rental.*;
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
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

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

    public void run() throws IOException, ReservationException {
        List<Car> cars = loadData(this.filePath);
        ICarRentalCompany company = new CarRentalCompany(this.companyName, cars);
        this.session.registerCompany(company);
    }

    public static List<Car> loadData(String datafile)
            throws ReservationException, NumberFormatException, IOException {

        List<Car> cars = new LinkedList<Car>();

        int nextuid = 0;

        //open file
        BufferedReader in = new BufferedReader(new FileReader(datafile));
        //while next line exists
        while (in.ready()) {
            //read line
            String line = in.readLine();
            //if comment: skip
            if(line.startsWith("#"))
                continue;
            //tokenize on ,
            StringTokenizer csvReader = new StringTokenizer(line, ",");
            //create new car type from first 5 fields
            CarType type = new CarType(csvReader.nextToken(),
                    Integer.parseInt(csvReader.nextToken()),
                    Float.parseFloat(csvReader.nextToken()),
                    Double.parseDouble(csvReader.nextToken()),
                    Boolean.parseBoolean(csvReader.nextToken()));
            System.out.println(type);
            //create N new cars with given type, where N is the 5th field
            for(int i = Integer.parseInt(csvReader.nextToken());i>0;i--){
                cars.add(new Car(nextuid++, type));
            }
        }

        return cars;
    }
}
