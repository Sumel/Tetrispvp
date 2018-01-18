package tetrispvp.network;

/**
 * Peer to peer connection options.
 */
public interface ConnectionContext {

    /**
     * Creates peer to peer connection between modules.
     *
     * @param address Peer address in format (hostname|ip)(:port)
     */
    void connect(String address);

    /**
     * Disconnects from peer if connected.
     */
    void disconnect();

    /**
     * @return The local address listening for peer to peer connections.
     */
    String thisAddress();
}
