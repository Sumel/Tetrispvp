package tetrispvp.network.detail.impl;

import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageReceiver;
import tetrispvp.network.MessageSender;

public class ConcreteMessageContext implements MessageContext {
    @Override
    public MessageSender sender() {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public MessageReceiver receiver() {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public String groupName() {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public MessageContext subgroup(String subgroupName) {
        throw new IllegalStateException("Not implemented.");
    }
}
