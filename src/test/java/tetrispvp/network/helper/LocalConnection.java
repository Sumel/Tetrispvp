package tetrispvp.network.helper;

import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.detail.LocalEndpoint;
import tetrispvp.network.detail.socket.SocketConnectionProvider;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class LocalConnection implements Connection {
    private final Queue<Object> nearEndMQ;
    private final Queue<Object> farEndMQ;
    private String address;

    private LocalConnection(Queue<Object> nearEndMQ, Queue<Object> farEndMQ) {
        this.nearEndMQ = nearEndMQ;
        this.farEndMQ = farEndMQ;
    }

    public LocalConnection(LocalEndpoint from) {
        address = from.getUID();
        this.nearEndMQ = new LinkedBlockingQueue<>();
        this.farEndMQ = new LinkedBlockingQueue<>();
    }

    @Override
    public void sendMessage(Object message) {
        farEndMQ.add(message);
        synchronized (farEndMQ) {
            farEndMQ.notify();
        }
    }

    @Override
    public Object receiveMessage() {
        if (nearEndMQ.isEmpty())
            try {
                synchronized (nearEndMQ) {
                    nearEndMQ.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return nearEndMQ.poll();
    }

    @Override
    public String thisAddress() {
        return address;
    }

    @Override
    public void close() {
        System.out.println("Connection closed.");
    }

    @Override
    public boolean isOpen() {
        throw new IllegalStateException("Not implemented.");
    }

    public LocalConnection getFarEnd() {
        return new LocalConnection(farEndMQ, nearEndMQ);
    }


}
