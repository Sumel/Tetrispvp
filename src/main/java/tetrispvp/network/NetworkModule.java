package tetrispvp.network;

/**
 * High level module for establishing a full-duplex peer to peer
 * connection and passing messages between peers.
 */
public interface NetworkModule {
    /**
     * @return Context for managing the connection.
     */
    ConnectionContext connectionContext();

    /**
     * @return Context for passing messages.
     */
    MessageContext messageContext();
}
