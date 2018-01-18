package tetrispvp.network.detail.impl;

import tetrispvp.network.ConnectionContext;
import tetrispvp.network.MessageContext;
import tetrispvp.network.NetworkModule;

public class ConcreteNetworkModule implements NetworkModule {

    ConcreteConnectionContext connectionContext = new
            ConcreteConnectionContext();
    ConcreteMessageContext messageContext = new ConcreteMessageContext(connectionContext);

    public ConcreteNetworkModule() {
        messageContext.activate();
    }

    @Override
    public ConnectionContext connectionContext() {
        return connectionContext;
    }

    @Override
    public MessageContext messageContext() {
        return messageContext;
    }

    @Override
    public void cleanUp() {
        connectionContext.disconnect();
        messageContext.deactivate();
    }
}
