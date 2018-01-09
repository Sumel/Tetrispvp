package tetrispvp.network.detail.impl;

import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageSender;
import tetrispvp.network.detail.Connection;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcreteMessageSender implements MessageSender {

    private final MessageContext parentContext;
    private final ConcreteConnectionContext connectionContext;
    private final Queue<Message> messageQueue = new ArrayDeque<>();
    private final Lock messageQueueLock = new ReentrantLock();
    private final Condition messageQueueNotEmpty = messageQueueLock
            .newCondition();
    private final Queue<Message> messageQueueBacklog = new ArrayDeque<>();
    private Thread sending = null;
    private boolean stopSending = false;
    private static final long sleepOnErrorMillis = 100;

    public ConcreteMessageSender(MessageContext parentContext,
                                 ConcreteConnectionContext connectionContext) {
        this.parentContext = parentContext;
        this.connectionContext = connectionContext;
    }

    @Override
    public void send(String messageName, Object with) {
        messageQueueLock.lock();
        try {
            messageQueue.add(new Message(messageName, with));
            if (messageQueue.size() == 1)
                messageQueueNotEmpty.signalAll();
        } finally {
            messageQueueLock.unlock();
        }
    }

    @Override
    public MessageContext context() {
        return parentContext;
    }

    public void startSending() {
        if (isSending())
            throw new IllegalStateException("Already sending.");

        stopSending = false;

        final ConcreteMessageSender sender = this;
        sending = new Thread(sender::sendingLoop);
        sending.start();
    }

    public boolean isSending() {
        return sending != null;
    }

    public void stopSending() {
        if (sending == null)
            return;

        stopSending = true;

        messageQueueLock.lock();

        try {
            messageQueueNotEmpty.notifyAll();
        } finally {
            messageQueueLock.unlock();
        }

        for (int i = 0; i < 5; i++) {
            try {
                sending.join(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (sending.isAlive())
            throw new IllegalStateException("Couldn't join sending thread.");
        sending = null;
    }

    private void sendingLoop() {
        while (!stopSending) {
            Message messageToSend = null;
            if (messageQueueBacklog.isEmpty()) {
                messageQueueLock.lock();
                try {
                    while (messageQueue.isEmpty()) {
                        try {
                            messageQueueNotEmpty.await(1, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (stopSending)
                            return;
                    }
                    messageToSend = messageQueue.remove();
                } finally {
                    messageQueueLock.unlock();
                }
            } else {
                messageToSend = messageQueueBacklog.peek();
            }

            Connection connection = connectionContext.getConnection();

            try {
                connection.sendMessage(messageToSend);
            } catch (IOException e) {

                if (messageQueueBacklog.isEmpty())
                    messageQueueBacklog.add(messageToSend);

                e.printStackTrace();

                try {
                    Thread.sleep(sleepOnErrorMillis);
                } catch (InterruptedException e1) {
                }

                connection.close();
                continue;
            }

            if (!messageQueueBacklog.isEmpty())
                messageQueueBacklog.remove();
        }
    }
}