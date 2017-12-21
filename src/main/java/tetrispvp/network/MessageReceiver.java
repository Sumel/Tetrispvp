package tetrispvp.network;

import java.util.regex.Pattern;

public interface MessageReceiver {
    void expect(String messageName, MessageHandler handler);

    void expect(Pattern messageName, MessageHandler handler);

    void unexpected(MessageHandler handler);

    void forgetAll();

    MessageContext context();
}
