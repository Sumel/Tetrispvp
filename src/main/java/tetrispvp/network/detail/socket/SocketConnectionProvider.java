package tetrispvp.network.detail.socket;

import com.sun.security.ntlm.Server;
import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionProvider;
import tetrispvp.network.detail.LocalEndpoint;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

public class SocketConnectionProvider implements ConnectionProvider {

    private int listenPort = 54225;

    @Override
    public Connection connect(LocalEndpoint from, String address) {

        String[] s = address.split(":");

        if (s.length != 2)
            throw new IllegalArgumentException("Bad address.");

        String host = s[0];

        try {
            Integer port = Integer.parseInt(s[1]);
            ServerSocket listen = new ServerSocket(listenPort);
            Socket peer = new Socket(host, port);

            return new SocketConnection(listen, peer, listenAddress());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid address.", e);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't connect.", e);
        }
    }

    @Override
    public Connection listen(LocalEndpoint from) {
        try {
            ServerSocket listen = new ServerSocket(listenPort);

            return new SocketConnection(listen, listenAddress());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't listen.", e);
        }
    }

    public void overrideListenPort(int port) {
        listenPort = port;
    }

    private String listenAddress() {
        return "127.0.0.1:" + listenPort;
    }
}
