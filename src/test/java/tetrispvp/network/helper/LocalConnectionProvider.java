package tetrispvp.network.helper;

import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionProvider;
import tetrispvp.network.detail.LocalEndpoint;

public class LocalConnectionProvider implements ConnectionProvider {
    @Override
    public Connection connect(LocalEndpoint from, String address) {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public Connection listen(LocalEndpoint from) {
        throw new IllegalStateException("Not implemented.");
    }
}
