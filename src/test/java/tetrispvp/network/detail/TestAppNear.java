package tetrispvp.network.detail;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class TestAppNear {

    @Test
    public void main() {
        LocalEndpoint here = new LocalEndpoint() {
            @Override
            public String getUID() {
                return "b6f4b2dc-2aa9-4426-a5d2-60c26fd0f3a4";
            }
        };

        boolean interactive = false;

        Scanner input = new Scanner(System.in);
        System.out.println("Host: ");
        String host = "";
        int port;

        if (interactive) {
            host = input.nextLine();
        }

        if (!host.isEmpty()) {
            System.out.println("Port: ");
            port = Integer.parseInt(input.nextLine());
        } else {
            host = "127.0.0.1";
            port = 52869;
        }

        String address = host + ":" + port;
        System.out.println("Connecting to " + address);

        Connection c = ConnectionFactory.connect(here, address);

        for (int i = 0; i < 32; i++) {
            try {
                String message = "Hello " + i + "!";
                c.sendMessage(message);
                System.out.println("Sending a message: " + message);
                String response = (String) c.receiveMessage();
                System.out.println("Received: " + response);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        c.close();
    }
}
