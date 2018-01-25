package powerUps.mocks;

public interface MessageHandler {
    void arrived(String messageName, Object with, MessageContext within);
    boolean shouldBeForgotten();
    void wasForgotten();
}
