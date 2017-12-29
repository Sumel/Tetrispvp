package tetrispvp.network;

import java.util.regex.Pattern;

/**
 * Allows managing what happens to received messages.
 */
public interface MessageReceiver {

    /**
     * @param messageName Matching message must have this exact name.
     * @param handler     Handler to be called when a matching message arrives
     *                    within this receiver's context.
     *                    Note: Handler call order is unspecified.
     */
    void expect(String messageName, MessageHandler handler);

    /**
     * @param messageName Pattern defining matching messages.
     * @param handler     Handler to be called when a matching message arrives
     *                    within this receiver's context.
     *                    Note: Handler call order is unspecified.
     */
    void expect(Pattern messageName, MessageHandler handler);

    /**
     * @param handler Handler to be called when a message arrives for
     *                which no handler has been specified.
     */
    void unexpected(MessageHandler handler);

    /**
     * Unregisters all handlers.
     */
    void forgetAll();

    /**
     * @return This receiver's context.
     */
    MessageContext context();
}
