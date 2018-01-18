package tetrispvp.network.helper;

import tetrispvp.network.*;
import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.detail.LocalEndpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ModuleITClientHelper {
    private final String address;

    public ModuleITClientHelper(String address) {
        this.address = address;
    }

    public class Message {
        String name;
        String contents;

        Message(String name, String contents) {
            this.name = name;
            this.contents = contents;
        }
    }

    public class Handler implements MessageHandler {

        private int actual = 0;

        @Override
        public synchronized void arrived(String messageName, Object with,
                                         MessageContext within) {
            String response = (String) with;
            System.out.println("Received: " + response);
            if (response.startsWith("This is a response to \"Hello "))
                actual++;
        }

        @Override
        public boolean shouldBeForgotten() {
            return false;
        }

        @Override
        public void wasForgotten() {

        }

        public int getActual() {
            return actual;
        }
    }

    public void run() {
        NetworkModule network = NetworkModuleFactory.getNetworkModule();

        ConnectionContext connection = network.connectionContext();
        MessageContext messages = network.messageContext();
        MessageReceiver receiver = messages.receiver();
        MessageSender sender = messages.sender();

        Handler handler = new Handler();
        receiver.expect("response", handler);

        connection.connect(address);

        int expected = 32;
        for (int i = 0; i < 32; i++) {
            String message = "Hello " + i + "!";
            sender.send("hello", message);
            System.out.println("Sending a message: " + message);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int actual = handler.getActual();
        if (expected != actual) {
            System.out.println("\nExpected message count: " + expected);
            System.out.println("Actual message count: " + actual);
        }

        network.cleanUp();
        System.exit(expected - actual);
    }
}
