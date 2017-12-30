package tetrispvp.network.detail;

public class ConnectionFactory {
    /**
     * Obtains a connection from current provider.
     */
    static public Connection connect(LocalEndpoint from, String toAddress) {
        throw new IllegalStateException("Not implemented.");
    }

    /**
     * Obtains a connection from current provider.
     * Doesn't connect to peer.
     */
    static public Connection listen(LocalEndpoint from) {
        throw new IllegalStateException("Not implemented.");
    }

    /**
     * Overrides the connection provider.
     */
    static public void overrideProvider(ConnectionProvider connectionProvider) {
        throw new IllegalStateException("Not implemented.");
    }

    /**
     * Resets the connection provider to the default one.
     */
    static public void resetProvider() {
        throw new IllegalStateException("Not implemented.");
    }

}
