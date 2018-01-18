package tetrispvp.network.helper;

import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.detail.LocalEndpoint;

import java.io.IOException;

public class ConnectionITServerHelper {
    private LocalEndpoint here = new LocalEndpoint() {
        @Override
        public String getUID() {
            return "ba518818-abad-4983-b3d2-45d7154c693d";
        }
    };

    public void run() {
        Connection c = ConnectionFactory.listen(here);

        System.out.println("Listening on " + c.thisAddress());

        for (int i = 0; i < 32; i++) {
            try {
                String message = (String) c.receiveMessage();
                System.out.println("Received: " + message);
                String response = "This is a response to \"" +
                        message + "\"";
                System.out.println("Sending a response: " + response);
                c.sendMessage(response);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        c.close();
    }
}
