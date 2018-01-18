package tetrispvp.network.detail.impl;

import tetrispvp.network.ConnectionContext;
import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageReceiver;
import tetrispvp.network.MessageSender;

public class ConcreteMessageContext implements MessageContext {

    private ConcreteMessageSender sender;
    private ConcreteMessageReceiver receiver;

    ConcreteMessageContext(ConcreteConnectionContext connectionContext) {
        sender = new ConcreteMessageSender(this, connectionContext);
        receiver = new ConcreteMessageReceiver(this, connectionContext);
    }

    @Override
    public MessageSender sender() {
        return sender;
    }

    @Override
    public MessageReceiver receiver() {
        return receiver;
    }

    public void activate() {
        if (!sender.isSending())
            sender.startSending();
        if (!receiver.isReceiving())
            receiver.startReceiving();
    }

    public boolean isActive() {
        return sender.isSending() && receiver.isReceiving();
    }

    public void deactivate() {
        sender.stopSending();
        receiver.stopReceiving();
    }
}
