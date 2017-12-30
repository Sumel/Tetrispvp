package tetrispvp.network.detail;

import java.util.ArrayDeque;
import java.util.Queue;

public class LocalConnection implements Connection {
    private final Queue<String> nearEndMQ;
    private final Queue<String> farEndMQ;

    private LocalConnection(Queue<String> nearEndMQ, Queue<String> farEndMQ) {
        this.nearEndMQ = nearEndMQ;
        this.farEndMQ = farEndMQ;
    }

    public LocalConnection() {
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

    @Override
    public String thisAddress() {
        throw new IllegalStateException("Not implemented.");
    }

    public LocalConnection getFarEnd() {
        return new LocalConnection(farEndMQ, nearEndMQ);
    }
}
