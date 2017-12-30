package tetrispvp.network.detail.socket;

import tetrispvp.network.detail.Connection;

public class SocketConnection implements Connection {
    @Override
    public void sendMessage(String message) {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public String receiveMessage() throws InterruptedException {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public String thisAddress() {
        throw new IllegalStateException("Not implemented.");
    }
}
