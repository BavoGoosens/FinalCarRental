package session;

import java.rmi.Remote;

/**
 * Created by Team Loco on 23/10/2014.
 */
public interface SessionManagerRemote extends Remote {

    // client name
    ManagerSession getManagerSessionRemote(String name);

    ReservationSession getReservationSessionRemote(String name);

}
