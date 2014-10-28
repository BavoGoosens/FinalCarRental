package client;

import rental.CarType;
import rental.Reservation;
import session.ManagerSessionRemote;
import session.ReservationSessionRemote;
import session.SessionManagerRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Team Loco on 21/10/2014.
 */
public class Client extends AbstractScriptedTripTest<ReservationSessionRemote, ManagerSessionRemote> {

    private SessionManagerRemote sessionManager;

    public Client(String scriptFile, String host, int port, String name) throws RemoteException, NotBoundException {
        super(scriptFile);
        Registry registry = LocateRegistry.getRegistry(host, port);
        this.sessionManager = (SessionManagerRemote) registry.lookup(name);
    }

    @Override
    protected ReservationSessionRemote getNewReservationSession(String name) throws Exception {
        return this.sessionManager.getReservationSessionRemote(name);
    }

    @Override
    protected ManagerSessionRemote getNewManagerSession(String name) throws Exception {
        return this.sessionManager.getManagerSessionRemote(name);
    }

    @Override
    protected void checkForAvailableCarTypes(ReservationSessionRemote reservationSession, Date start, Date end) throws Exception {
        reservationSession.getAvailableCarTypes(start, end);
    }

    @Override
    protected String getCheapestCarType(ReservationSessionRemote reservationSession, Date start, Date end) throws Exception {
        return reservationSession.getCheapestCarType(start, end);
    }

    @Override
    protected void addQuoteToSession(ReservationSessionRemote reservationSession, Date start, Date end, String carType, String carRentalName) throws Exception {
        reservationSession.createQuote(start,end,carType,carRentalName);
    }

    @Override
    protected List<Reservation> confirmQuotes(ReservationSessionRemote reservationSession) throws Exception {
        return reservationSession.confirmQuotes();
    }

    @Override
    protected int getNumberOfReservationsBy(ManagerSessionRemote ms, String clientName) throws Exception {
        return ms.getNbOfReservations(clientName);
    }

    @Override
    protected Set<String> getBestClients(ManagerSessionRemote ms) throws Exception {
        return ms.getBestClients();
    }

    @Override
    protected int getNumberOfReservationsForCarType(ManagerSessionRemote ms, String carRentalCompanyName, String carType) throws Exception {
        return ms.getNbOfReservations(carRentalCompanyName, carType);
    }

    @Override
    protected CarType getMostPopularCarTypeIn(ManagerSessionRemote ms, String carRentalCompanyName) throws Exception {
        return ms.getMostPopularCarType(carRentalCompanyName);
    }
}
