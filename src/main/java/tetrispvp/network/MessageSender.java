package tetrispvp.network;

/**
 * Sends messages to the peer.
 */
public interface MessageSender {
    /**
     * Sends a message to the peer asynchronously.
     *
     * @param messageName Message name.
     * @param with        Message parameter. May be null.
     */
    void send(String messageName, Object with);

    /**
     * @return This sender's context.
     */
    MessageContext context();
}
