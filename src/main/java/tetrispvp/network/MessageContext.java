package tetrispvp.network;

/**
 * Message context responsible for managing all messages being passed
 * between peers.
 */
public interface MessageContext {

    /**
     * @return A sender for messages in this context.
     */
    MessageSender sender();

    /**
     * @return A receiver for messages in this context.
     */
    MessageReceiver receiver();
}
