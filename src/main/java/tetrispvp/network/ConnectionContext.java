package tetrispvp.network;

public interface ConnectionContext {
    void connect(String address);

    void disconnect();
}
