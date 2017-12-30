package tetrispvp.network.detail.impl;

import tetrispvp.network.ConnectionContext;
import tetrispvp.network.MessageContext;
import tetrispvp.network.NetworkModule;

public class ConcreteNetworkModule implements NetworkModule {

    @Override
    public ConnectionContext connectionContext() {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public MessageContext messageContext() {
        throw new IllegalStateException("Not implemented.");
    }
}
