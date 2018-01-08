package tetrispvp.network.detail.socket;

import tetrispvp.network.detail.Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class SocketConnection implements Connection {

    private final ServerSocket listen;
    private final String address;
    private Socket peer;
    private Thread listening = null;
    private boolean stopListening = false;
    private boolean errorOnAccept = false;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    SocketConnection(ServerSocket listen, String address) {
        this.listen = listen;
        this.peer = null;
        this.address = address;

        startListening();
    }

    SocketConnection(ServerSocket listen, Socket peer, String address) throws IOException {
        this.listen = listen;
        this.peer = peer;
        this.address = address;

        initPeer();
        startListening();
    }

    @Override
    public void sendMessage(Object message) throws IOException {
        if (message == null)
            throw new NullPointerException("Message can't be null.");

        waitForPeer();
        out.writeObject(message);
    }

    @Override
    public Object receiveMessage() throws IOException, ClassNotFoundException {
        waitForPeer();

        return in.readObject();
    }

    @Override
    public String thisAddress() {
        return address;
    }

    public void startListening() {

        final SocketConnection connection = this;
        this.listening = new Thread(connection::listeningLoop);
        this.listening.start();
    }

    private void listeningLoop() {
        try {
            listen.setSoTimeout(100);
        } catch (SocketException e) {
            throw new RuntimeException("Couldn't set socket timeout.", e);
        }

        while (!stopListening) {
            try {
                if (peer == null) {
                    peer = listen.accept();
                    initPeer();
                } else {
                    Thread.sleep(100);
                }
            } catch (SocketTimeoutException e) {
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                errorOnAccept = true;
            }
        }
    }

    @Override
    public void close() {
        if (listening == null)
            return;

        stopListening = true;

        for (int i = 0; i < 5; i++) {
            try {
                listening.join(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (listening.isAlive())
            throw new IllegalStateException("Couldn't join listening thread.");
    }

    private void initPeer() throws IOException {
        try {
            out = new ObjectOutputStream(peer.getOutputStream());
            in = new ObjectInputStream(peer.getInputStream());
        } finally {
            if (out == null || in == null) {
                out = null;
                in = null;
            }
        }
    }

    private void waitForPeer() {
        while (peer == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
