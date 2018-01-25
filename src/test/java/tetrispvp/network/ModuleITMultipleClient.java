package tetrispvp.network;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.detail.LocalEndpoint;

import java.io.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ModuleITMultipleClient {

    static int port = 52869;
    private static LocalEndpoint here = () -> "b6f4b2dc-2aa9-4426-a5d2-60c26fd0f3a4";
    static String host = "127.0.0.1";
    static String address = host + ":" + port;

    @Test
    public void testSimpleMessageSend() throws InterruptedException, IOException {
        startTest();
        Connection c = ConnectionFactory.connect(here, address);

        try {
            String message = "Hello im singleMessageTest";

            System.out.println("Sending a message: " + message);
            c.sendMessage("singleMessageTest");

            c.sendMessage(message);
            System.out.println("Collecting a response");
            String response = (String) c.receiveMessage();
            System.out.println("Received: " + response);
            Assert.assertEquals(response, "This is a response to singleMessageTest.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            c.close();
        }
        finishtTest();
    }

    @Test
    public void testMultipleMessageSend() throws InterruptedException, IOException {
        startTest();
        Connection c = ConnectionFactory.connect(here, address);
        int max = 150, min = 15;
        try {
            c.sendMessage("multipleMessageTest");
            int messagesToSend = new Random().nextInt(max - min + 1) + min;
            System.out.println("Sending information about messages quantity");
            c.sendMessage(messagesToSend);
            System.out.println("Sending " + messagesToSend + " messages");
            for (int i = 0; i < messagesToSend; i++) {
                c.sendMessage("Message" + i);
            }
            Assert.assertEquals("Received " + messagesToSend + " messages", c.receiveMessage());
            System.out.println("Successfully sent " + messagesToSend + " messages");

            Assert.assertEquals("ValuesOK", c.receiveMessage());
            System.out.println("Values confirmed");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            c.close();
        }
        finishtTest();
    }

    @Test
    public void testParallelMessageSend() throws InterruptedException, IOException, ClassNotFoundException {
        startTest();
        System.out.println("Testing parallel message handle");

        Connection c = ConnectionFactory.connect(here, address);
        try {
            c.sendMessage("parallelMessageTest");
            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 5; i++) {
                int currentThread = i;
                threadPool.execute(() -> {
                    try {
                        System.out.println("Starting sending thread: " + currentThread);
                        c.sendMessage("Sending thread: " + currentThread);
                    } catch (IOException e) {
                        Assert.fail("Error occurred while sending from thread: " + currentThread + ". Exception: " + e);
                    }
                });
            }
            try {
                System.out.println("Main thread is waiting for thread termination");
                threadPool.awaitTermination(5, TimeUnit.SECONDS);
                System.out.println("Sending completed");
            } catch (InterruptedException e) {
                Assert.fail("Exception occurred while awaiting threads termination: " + e);
            }
            Assert.assertEquals("Recieved 5 messages", c.receiveMessage());
            finishtTest();
        } finally {
            c.close();
        }
    }

    private void startTest() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("==================Test started===================");
    }

    private void finishtTest() {
        System.out.println("=================Test finished===================");
        System.out.println("=================================================");
        System.out.println();
    }

    @AfterClass
    public static void killServer() throws IOException {
        Connection c = ConnectionFactory.connect(here, address);
        c.sendMessage("Kill");
    }
}
