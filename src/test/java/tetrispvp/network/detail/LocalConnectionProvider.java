package tetrispvp.network.detail;

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
