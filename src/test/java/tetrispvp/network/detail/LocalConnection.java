package tetrispvp.network.detail;

import java.util.ArrayDeque;
import java.util.Queue;

public class LocalConnection implements Connection {
    private final Queue<Object> nearEndMQ;
    private final Queue<Object> farEndMQ;

    private LocalConnection(Queue<Object> nearEndMQ, Queue<Object> farEndMQ) {
        this.nearEndMQ = nearEndMQ;
        this.farEndMQ = farEndMQ;
    }

    public LocalConnection() {
        this.nearEndMQ = new ArrayDeque<>();
        this.farEndMQ = new ArrayDeque<>();
    }

    @Override
    public void sendMessage(Object message) {
        synchronized (farEndMQ) {
            farEndMQ.offer(message);
            if (farEndMQ.size() == 1)
                notify();
        }
    }

    @Override
    public Object receiveMessage() {
        synchronized (nearEndMQ) {
            while (nearEndMQ.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return nearEndMQ.poll();
        }
    }

    @Override
    public String thisAddress() {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public void close() {
        throw new IllegalStateException("Not implemented.");
    }

    public LocalConnection getFarEnd() {
        return new LocalConnection(farEndMQ, nearEndMQ);
    }
}
