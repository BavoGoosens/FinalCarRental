package client;

/**
 * Created by Team Loco on 21/10/2014.
 */
public class RunClient {

    public static void main(String[] args) throws Exception {
        Client client = new Client("trips");
        client.run();
    }
}