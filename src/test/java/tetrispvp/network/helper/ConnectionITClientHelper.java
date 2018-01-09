package tetrispvp.network.helper;

import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.detail.LocalEndpoint;

import java.io.IOException;

public class ConnectionITClientHelper {
    private final String address;
    private LocalEndpoint here = new LocalEndpoint() {
        @Override
        public String getUID() {
            return "b6f4b2dc-2aa9-4426-a5d2-60c26fd0f3a4";
        }
    };

    public ConnectionITClientHelper(String address) {
        this.address = address;
    }

    public void run() {
        Connection c = ConnectionFactory.connect(here, address);

        int expected = 32;
        int actual = 0;
        for (int i = 0; i < 32; i++) {
            try {
                String message = "Hello " + i + "!";
                c.sendMessage(message);
                System.out.println("Sending a message: " + message);
                String response = (String) c.receiveMessage();
                System.out.println("Received: " + response);
                if (response.equals("This is a response to \"" + message + "\""))
                    actual++;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        c.close();

        if (expected != actual) {
            System.out.println("\nExpected message count: " + expected);
            System.out.println("Actual message count: " + actual);
        }
        System.exit(expected - actual);
    }
}
