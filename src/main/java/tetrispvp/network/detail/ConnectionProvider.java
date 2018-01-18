package tetrispvp.network.detail;

/**
 * Provides a connection.
 */
public interface ConnectionProvider {
    /**
     * @param from    Local endpoint.
     * @param address Remote address.
     * @return A connection if successfully connected.
     */
    Connection connect(LocalEndpoint from, String address);

    /**
     * @param from Local endpoint.
     * @return A connection which enables a peer to connect.
     */
    Connection listen(LocalEndpoint from);
}
