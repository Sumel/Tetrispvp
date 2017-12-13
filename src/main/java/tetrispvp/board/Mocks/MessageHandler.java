package tetrispvp.board.Mocks;

public interface MessageHandler {
    void arrived(String messageName, Object with, MessageContext within);
}
