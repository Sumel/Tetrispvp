package tetrispvp.network.helper;

import sun.nio.ch.Net;
import tetrispvp.network.*;
import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.detail.LocalEndpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModuleITServerHelper {

    public class Message {
        String name;
        String contents;

        Message(String name, String contents) {
            this.name = name;
            this.contents = contents;
        }
    }

    public class Handler implements MessageHandler {

        private final MessageSender sender;
        private int count = 0;

        public Handler(MessageSender sender) {
            this.sender = sender;
        }

        @Override
        public synchronized void arrived(String messageName, Object with,
                                         MessageContext within) {
            String message = (String) with;
            System.out.println("Received: " + message);
            String response = "This is a response to \"" +
                    message + "\"";
            System.out.println("Sending a response: " + response);
            sender.send("response", response);
            count++;
        }

        @Override
        public boolean shouldBeForgotten() {
            return false;
        }

        @Override
        public void wasForgotten() {

        }

        public int getCount() {
            return count;
        }
    }

    public void run() {
        NetworkModule network = NetworkModuleFactory.getNetworkModule();

        ConnectionContext connection = network.connectionContext();
        MessageContext messages = network.messageContext();
        MessageReceiver receiver = messages.receiver();
        MessageSender sender = messages.sender();

        System.out.println("Listening on " +
                connection.thisAddress());

        Handler handler = new Handler(sender);
        receiver.expect("hello", handler);

        while (handler.getCount() != 32)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        network.cleanUp();
    }
}
