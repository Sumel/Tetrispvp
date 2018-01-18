package powerUps.mocks;

public interface MessageReceiver {
    void expect(String messageName, MessageHandler handler);
}
