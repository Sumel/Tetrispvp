package tetrispvp.network.detail;

import tetrispvp.network.detail.socket.SocketConnectionProvider;

public class ConnectionFactory {

    /**
     * Obtains a connection from current provider.
     */
    static public Connection connect(LocalEndpoint from, String toAddress) {
        return provider.connect(from, toAddress);
    }

    /**
     * Obtains a connection from current provider.
     * Doesn't connect to peer.
     */
    static public Connection listen(LocalEndpoint from) {
        return provider.listen(from);
    }

    /**
     * Overrides the connection provider.
     */
    static public void overrideProvider(ConnectionProvider connectionProvider) {
        if (connectionProvider == null)
            throw new NullPointerException("Connection provider shouldn't be" +
                    " null.");
        provider = connectionProvider;
    }

    /**
     * Resets the connection provider to the default one.
     */
    static public void resetProvider() {
        provider = new SocketConnectionProvider();
    }

    static private ConnectionProvider provider = new SocketConnectionProvider();

    public static ConnectionProvider getProvider() {
        return provider;
    }
}
