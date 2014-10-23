package session;

import java.rmi.Remote;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class SessionManager implements SessionManagerRemote {


    @Override
    public ManagerSessionRemote getManagerSessionRemote() {
        // TODO
        return null;
    }

    @Override
    public ReservationSessionRemote getReservationSessionRemote() {
        //TODO
        return null;
    }
}
