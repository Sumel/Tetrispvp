package tetrispvp.network.detail.impl;

import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageHandler;
import tetrispvp.network.MessageReceiver;

import java.util.regex.Pattern;

public class GlobalMessageReceiver implements MessageReceiver {
    @Override
    public void expect(String messageName, MessageHandler handler) {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public void expect(Pattern messageName, MessageHandler handler) {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public void unexpected(MessageHandler handler) {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public void forgetAll() {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public MessageContext context() {
        throw new IllegalStateException("Not implemented.");
    }
}
