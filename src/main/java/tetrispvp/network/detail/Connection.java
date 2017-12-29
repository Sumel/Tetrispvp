package tetrispvp.network.detail;

/**
 * Wrapper for a full-duplex connection allowing binary transmission.
 */
public interface Connection {

    /**
     * Sends a binary message synchronously.
     */
    void sendMessage(String message);

    /**
     * Receives a binary message synchronously.
     */
    String receiveMessage() throws java.lang.InterruptedException;
}
