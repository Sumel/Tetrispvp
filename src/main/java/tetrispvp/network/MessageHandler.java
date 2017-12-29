package tetrispvp.network;

/**
 * An interface for a user to inherit from in order to be able to
 * subscribe to message-related events.
 */
public interface MessageHandler {
    /**
     * Called when a message arrives, and this handler is specified
     * for it.
     *
     * @param messageName Name of the message that arrived.
     * @param with        Object that was passed as the message parameter. May be
     *                    null.
     * @param within      The message context.
     */
    void arrived(String messageName, Object with, MessageContext within);

    /**
     * @return true if this handler should no longer be considered when
     * handling messages.
     */
    boolean shouldBeForgotten();

    /**
     * Called when this handler ceases to be considered for handling
     * messages.
     * Note: The context is unspecified.
     */
    void wasForgotten();
}
