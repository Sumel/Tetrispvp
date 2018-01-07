package tetrispvp.network.detail;

import org.junit.Test;
import tetrispvp.network.detail.socket.SocketConnectionProvider;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class TestAppFar {

    @Test
    public void main() {
        LocalEndpoint here = new LocalEndpoint() {
            @Override
            public String getUID() {
                return "ba518818-abad-4983-b3d2-45d7154c693d";
            }
        };

        SocketConnectionProvider provider = (SocketConnectionProvider)
                ConnectionFactory.getProvider();
        provider.overrideListenPort(52869);

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
