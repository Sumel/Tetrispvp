package tetrispvp.board.NetworkIntegration;

import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageSender;
import tetrispvp.network.NetworkModule;
import tetrispvp.network.NetworkModuleFactory;

public class LastMessageSender implements MessageSender {

    private final MessageSender sender;

    public LastMessageSender() {
        NetworkModule module = NetworkModuleFactory.last();
        if (module == null)
            throw new NullPointerException("Network module was not created.");
        this.sender = module.messageContext().sender();
    }


    @Override
    public void send(String messageName, Object with) {
        sender.send(messageName, with);
    }

    @Override
    public MessageContext context() {
        return sender.context();
    }
}
