package tetrispvp.network;

import org.junit.Assert;
import org.junit.Test;
import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.detail.LocalEndpoint;
import tetrispvp.network.detail.socket.SocketConnectionProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ModuleITMultipleServer {

    int port = 52869;
    private LocalEndpoint here = () -> "ba518818-abad-4983-b3d2-45d7154c693d";
    String host = "127.0.0.1";
    String message;
    String address = host + ":" + port;
    Connection serverConnection;
    SocketConnectionProvider provider;

    @Test
    public void initialiseServer() {
        try {

            provider = (SocketConnectionProvider)
                    ConnectionFactory.getProvider();
            provider.overrideListenPort(port);
            serverConnection = ConnectionFactory.listen(here);
            System.out.println("Started listening on: " + address);
            do {
                message = (String) serverConnection.receiveMessage();
                try {
                    if (message.equals("singleMessageTest")) {
                        try {
                            startTest();
                            handleSingleMessageTest();
                        } catch (Exception e) {
                            Assert.fail("singleMessageTest failed due to exception: " + e + "!");
                        } finally {
                            finishtTest();
                        }
                    }
                    if (message.equals("multipleMessageTest")) {
                        try {
                            startTest();
                            handlemultipleMessageTest();
                        } catch (Exception e) {
                            Assert.fail("singleMessageTest failed due to exception: " + e + "!");
                        } finally {
                            finishtTest();
                        }
                    }
                    if (message.equals("parallelMessageTest")) {
                        try {
                            startTest();
                            handleParallelMessageTest();
                        } catch (Exception e) {
                            Assert.fail("singleMessageTest failed due to exception: " + e + "!");
                        } finally {
                            finishtTest();
                        }
                    }
                } finally {
                    restartServer();
                }
            } while (!message.equals("Kill"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void handlemultipleMessageTest() throws IOException, ClassNotFoundException {
        System.out.println("Testing multiple message handle");
        int messagesToReceive = (int) serverConnection.receiveMessage();
        List<String> messagesList = new ArrayList();
        for (int i = 0; i < messagesToReceive; i++) {
            messagesList.add((String) serverConnection.receiveMessage());
        }
        serverConnection.sendMessage("Received " + messagesList.size() + " messages");
        System.out.println("Received " + messagesList.size() + " messages");

        System.out.println("Checking messages values");

        for (int i = 0; i < messagesToReceive; i++) {
            Assert.assertEquals("Message" + i, messagesList.get(i));
        }
        serverConnection.sendMessage("ValuesOK");
    }

    private void handleSingleMessageTest() throws IOException, ClassNotFoundException {
        System.out.println("Testing single message handle");
        message = (String) serverConnection.receiveMessage();
        System.out.println("Received single message: " + message);
        String response = "This is a response to singleMessageTest.";
        System.out.println("Sending a response: " + response);
        serverConnection.sendMessage(response);
    }

    private void handleParallelMessageTest() throws IOException, ClassNotFoundException {
        final boolean[] allPassed = {true};
        System.out.println("Testing parallel message handle");
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            int currentThread = i;
            threadPool.execute(() -> {
                try {
                    System.out.println("Started thread: " + currentThread);
                    System.out.println("Recieved a message from sending thread: " + serverConnection.receiveMessage());
                } catch (IOException | ClassNotFoundException e) {
                    allPassed[0] = false;
                    System.out.println("Exception in thread: " + currentThread);
                }
            });
        }
        try {
            System.out.println("Waiting for thread termination");
            threadPool.awaitTermination(10, TimeUnit.SECONDS);
            if (allPassed[0] == false) {
                Assert.fail("One of threads throw an exception.");
            }
        } catch (InterruptedException e) {
            System.out.println("Exception occurred while awaiting threads termination: " + e);
        }
        serverConnection.sendMessage("Recieved 5 messages");
    }


    private void restartServer() {
        serverConnection.close();
        serverConnection = ConnectionFactory.listen(here);
        System.out.println();
    }

    private void startTest() {
//        System.out.println();
        System.out.println("=================================================");
        System.out.println("==================Test started===================");
    }

    private void finishtTest() {
        System.out.println("=================Test finished===================");
        System.out.println("=================================================");
//        System.out.println();
    }
}
