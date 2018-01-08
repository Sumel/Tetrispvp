package tetrispvp.network.detail.impl;

import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageHandler;
import tetrispvp.network.MessageReceiver;
import tetrispvp.network.detail.Connection;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class ConcreteMessageReceiver implements MessageReceiver {

    private static int defaultThreads = 8;
    private final MessageContext parentContext;
    private final ConcreteConnectionContext connectionContext;
    private Thread receiving = null;
    private HandlerStore store;
    private final Lock lock = new ReentrantLock();
    private boolean stopReceiving;

    public ConcreteMessageReceiver(MessageContext parentContext,
                                   ConcreteConnectionContext connectionContext) {
        this.parentContext = parentContext;
        this.store = new HandlerStore(parentContext, defaultThreads);
        this.connectionContext = connectionContext;
    }

    @Override
    public void expect(String messageName, MessageHandler handler) {
        lock.lock();
        try {
            store.expect(messageName, handler);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public synchronized void expect(Pattern messageName, MessageHandler handler) {
        lock.lock();
        try {
            store.expect(messageName, handler);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public synchronized void unexpected(MessageHandler handler) {
        lock.lock();
        try {
            store.unexpected(handler);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void forgetAll() {
        lock.lock();
        try {
            store.forgetAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public MessageContext context() {
        return parentContext;
    }

    public void startReceiving() {
        if (isReceiving())
            throw new IllegalStateException("Already receiving.");

        stopReceiving = false;

        final ConcreteMessageReceiver receiver = this;
        receiving = new Thread(receiver::receivingLoop);
        receiving.start();
    }

    public void stopReceiving() {
        if (receiving == null)
            return;

        stopReceiving = true;

        for (int i = 0; i < 5; i++) {
            try {
                receiving.join(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (receiving.isAlive())
            throw new IllegalStateException("Couldn't join receiving thread.");
        receiving = null;
    }

    public boolean isReceiving() {
        return receiving != null;
    }

    private void receivingLoop() {
        while (!stopReceiving) {
            Connection connection = connectionContext.getConnection();

            try {
                Object received = connection.receiveMessage();

                if (received instanceof Message) {
                    Message message = (Message) received;

                    lock.lock();
                    try {
                        store.received(message.name(), message.with());
                    } finally {
                        lock.unlock();
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                }
            }
        }
    }
}
