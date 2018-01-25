package tetrispvp.network.helper;

import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.detail.ConnectionProvider;
import tetrispvp.network.detail.LocalEndpoint;
import tetrispvp.network.detail.socket.SocketConnection;

import java.util.HashMap;
import java.util.Map;

public class LocalConnectionProvider implements ConnectionProvider {

    private Map<String, LocalConnection> addressMap = new HashMap<>();

    @Override
    public Connection connect(LocalEndpoint from, String address) {
        return addressMap.get(from.getUID()).getFarEnd();
    }

    @Override
    public Connection listen(LocalEndpoint from) {

        addressMap.put(from.getUID(), new LocalConnection(from));
        return addressMap.get(from.getUID());
    }
}

