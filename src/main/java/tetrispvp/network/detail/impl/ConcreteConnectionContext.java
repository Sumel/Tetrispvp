package tetrispvp.network.detail.impl;

import tetrispvp.network.ConnectionContext;
import tetrispvp.network.detail.LocalEndpoint;

public class ConcreteConnectionContext implements ConnectionContext, LocalEndpoint {

    @Override
    public void connect(String address) {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public void disconnect() {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public String thisAddress() {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public String getUID() {
        throw new IllegalStateException("Not implemented.");
    }
}
