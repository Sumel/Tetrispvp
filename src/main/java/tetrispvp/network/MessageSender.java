package tetrispvp.network;

public interface MessageSender {
    void send(String messageName, Object with);

    MessageContext context();
}
