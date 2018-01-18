package tetrispvp.board.NetworkIntegration;

import tetrispvp.network.*;

import java.util.regex.Pattern;

public class LastMessageReceiver implements MessageReceiver {

    private final MessageReceiver receiver;

    public LastMessageReceiver() {
        NetworkModule module = NetworkModuleFactory.last();
        if (module == null)
            throw new NullPointerException("Network module was not created.");
        this.receiver = module.messageContext().receiver();
    }

    @Override
    public void expect(String messageName, MessageHandler handler) {
        receiver.expect(messageName, handler);
    }

    @Override
    public void expect(Pattern messageName, MessageHandler handler) {
        receiver.expect(messageName, handler);
    }

    @Override
    public void unexpected(MessageHandler handler) {
        receiver.unexpected(handler);
    }

    @Override
    public void forgetAll() {
        receiver.forgetAll();
    }

    @Override
    public MessageContext context() {
        return receiver.context();
    }
}
