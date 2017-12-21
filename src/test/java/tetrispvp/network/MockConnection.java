package tetrispvp.network;

import tetrispvp.network.detail.Connection;

import java.util.ArrayDeque;
import java.util.Queue;

public class MockConnection implements Connection {
    private final Queue<String> nearEndMQ;
    private final Queue<String> farEndMQ;

    private MockConnection(Queue<String> nearEndMQ, Queue<String> farEndMQ) {
        this.nearEndMQ = nearEndMQ;
        this.farEndMQ = farEndMQ;
    }

    public MockConnection() {
        this.nearEndMQ = new ArrayDeque<>();
        this.farEndMQ = new ArrayDeque<>();
    }

    @Override
    public void sendMessage(String message) {
        synchronized (farEndMQ) {
            farEndMQ.offer(message);
            if (farEndMQ.size() == 1)
                notify();
        }
    }

    @Override
    public String receiveMessage() throws java.lang.InterruptedException {
        synchronized (nearEndMQ) {
            while (nearEndMQ.isEmpty())
                wait();

            return nearEndMQ.poll();
        }
    }

    public MockConnection getFarEnd() {
        return new MockConnection(farEndMQ, nearEndMQ);
    }
}
