package tetrispvp.network.detail;

import java.io.IOException;

/**
 * Wrapper for a full-duplex connection allowing binary transmission.
 */
public interface Connection {

    /**
     * Sends a binary message synchronously.
     */
    void sendMessage(Object message) throws IOException;

    /**
     * Receives a binary message synchronously.
     */
    Object receiveMessage() throws IOException, ClassNotFoundException;

    /**
     * Returns the address of this connection for the other peer to connect to.
     */
    String thisAddress();

    void close();

    boolean isOpen();
}
