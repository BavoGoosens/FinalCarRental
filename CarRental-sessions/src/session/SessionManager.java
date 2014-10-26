package session;

import java.rmi.Remote;

/**
 * Created by Team Loco on 22/10/2014.
 */
public class SessionManager implements SessionManagerRemote {

    public static void main(String[] args){

    }

    @Override
    public ManagerSession getManagerSessionRemote(String name) {
        return null;
    }

    @Override
    public ReservationSession getReservationSessionRemote(String name) {
        return null;
    }
}
