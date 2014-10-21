package client;

import rental.CarType;
import rental.Reservation;
import session.ManagerSession;
import session.ReservationSession;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Team Loco on 21/10/2014.
 */
public class Client extends AbstractScriptedTripTest<ReservationSession,ManagerSession> {

    public Client(String scriptFile) {
        super(scriptFile);
    }

    @Override
    protected ReservationSession getNewReservationSession(String name) throws Exception {
        return null;
    }

    @Override
    protected ManagerSession getNewManagerSession(String name) throws Exception {
        return null;
    }

    @Override
    protected void checkForAvailableCarTypes(ReservationSession reservationSession, Date start, Date end) throws Exception {

    }

    @Override
    protected String getCheapestCarType(ReservationSession reservationSession, Date start, Date end) throws Exception {
        return null;
    }

    @Override
    protected void addQuoteToSession(ReservationSession reservationSession, Date start, Date end, String carType, String carRentalName) throws Exception {

    }

    @Override
    protected List<Reservation> confirmQuotes(ReservationSession reservationSession) throws Exception {
        return null;
    }

    @Override
    protected int getNumberOfReservationsBy(ManagerSession ms, String clientName) throws Exception {
        return 0;
    }

    @Override
    protected Set<String> getBestClients(ManagerSession ms) throws Exception {
        return null;
    }

    @Override
    protected int getNumberOfReservationsForCarType(ManagerSession ms, String carRentalCompanyName, String carType) throws Exception {
        return 0;
    }

    @Override
    protected CarType getMostPopularCarTypeIn(ManagerSession ms, String carRentalCompanyName) throws Exception {
        return null;
    }
}
