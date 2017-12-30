package tetrispvp.network.detail.impl;

import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageSender;

public class GlobalMessageSender implements MessageSender {
    @Override
    public void send(String messageName, Object with) {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public MessageContext context() {
        throw new IllegalStateException("Not implemented.");
    }
}
