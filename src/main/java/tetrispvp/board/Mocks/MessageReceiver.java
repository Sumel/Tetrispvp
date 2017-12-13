package tetrispvp.board.Mocks;

public interface MessageReceiver {
    void expect(String messageName, MessageHandler handler);
}
