package tetrispvp.network.detail.impl;

import tetrispvp.network.MessageHandler;

import java.util.regex.Pattern;

public class HandlerStore {
    public void expect(String messageName, MessageHandler handler) {
        throw new IllegalStateException("Not implemented.");
    }

    public void expect(Pattern messageName, MessageHandler handler) {
        throw new IllegalStateException("Not implemented.");
    }

    public void unexpected(MessageHandler handler) {
        throw new IllegalStateException("Not implemented.");
    }

    public void forgetAll() {
        throw new IllegalStateException("Not implemented.");
    }

    public void received(String messageName, Object with) {
        throw new IllegalStateException("Not implemented.");
    }
}
