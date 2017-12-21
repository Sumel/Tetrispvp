package tetrispvp.network.detail;

public interface Connection {
    void sendMessage(String message);

    String receiveMessage() throws java.lang.InterruptedException;
}
