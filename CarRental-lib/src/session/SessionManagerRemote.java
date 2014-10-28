package session;

import java.rmi.Remote;

/**
 * Created by Team Loco on 23/10/2014.
 */
public interface SessionManagerRemote extends Remote {

    // client name
    ManagerSessionRemote getManagerSessionRemote(String name);

    ReservationSessionRemote getReservationSessionRemote(String name);

    void closeManagerSession(ManagerSessionRemote session);

    void closeReservationSession(ReservationSessionRemote session);
}
