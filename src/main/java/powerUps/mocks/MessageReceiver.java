package powerUps.mocks;

public interface MessageReceiver {
    void expect(String messageName, tetrispvp.network.MessageHandler handler);
}
