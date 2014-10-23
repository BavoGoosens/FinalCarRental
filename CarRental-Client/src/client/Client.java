package client;

import rental.CarType;
import rental.Reservation;
import session.ManagerSession;
import session.ReservationSession;
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
public class Client extends AbstractScriptedTripTest<ReservationSession,ManagerSession> {

    private SessionManagerRemote sessionManager;

    public Client(String scriptFile, String host, int port, String name) throws RemoteException, NotBoundException {
        super(scriptFile);
        Registry registry = LocateRegistry.getRegistry(host, port);
        this.sessionManager = (SessionManagerRemote) registry.lookup(name);
    }

    @Override
    protected ReservationSession getNewReservationSession(String name) throws Exception {
        return this.sessionManager.getReservationSessionRemote(name);
    }

    @Override
    protected ManagerSession getNewManagerSession(String name) throws Exception {
        return this.sessionManager.getManagerSessionRemote(name);
    }

    @Override
    protected void checkForAvailableCarTypes(ReservationSession reservationSession, Date start, Date end) throws Exception {
        reservationSession.getAvailableCarTypes(start, end);
    }

    @Override
    protected String getCheapestCarType(ReservationSession reservationSession, Date start, Date end) throws Exception {
        return reservationSession.getCheapestCarType(start, end);
    }

    @Override
    protected void addQuoteToSession(ReservationSession reservationSession, Date start, Date end, String carType, String carRentalName) throws Exception {
        reservationSession.createQuote(start,end,carType,carRentalName);
    }

    @Override
    protected List<Reservation> confirmQuotes(ReservationSession reservationSession) throws Exception {
        return reservationSession.confirmQuotes();
    }

    @Override
    protected int getNumberOfReservationsBy(ManagerSession ms, String clientName) throws Exception {
        return ms.getNbOfReservations(clientName);
    }

    @Override
    protected Set<String> getBestClients(ManagerSession ms) throws Exception {
        return ms.getBestClients();
    }

    @Override
    protected int getNumberOfReservationsForCarType(ManagerSession ms, String carRentalCompanyName, String carType) throws Exception {
        return ms.getNbOfReservations(carRentalCompanyName, carType);
    }

    @Override
    protected CarType getMostPopularCarTypeIn(ManagerSession ms, String carRentalCompanyName) throws Exception {
        return ms.getMostPopularCarType(carRentalCompanyName);
    }
}
