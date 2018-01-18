package powerUps.mocks;

import java.util.HashMap;

public class MockNetwork implements MessageSender,MessageReceiver {
    private MockNetwork opponent;
    private HashMap<String, tetrispvp.network.MessageHandler> expected;

    public MockNetwork(MockNetwork opponent) {
        this.opponent = opponent;
        this.expected = new HashMap<>();
    }

    public MockNetwork(){
        this.expected = new HashMap<>();
    }

    @Override
    public void expect(String messageName, tetrispvp.network.MessageHandler handler) {
        expected.putIfAbsent(messageName, handler);
    }

    @Override
    public void send(String messageName, Object with) {
        this.opponent.receive(messageName, with);
    }

    private void receive(String messageName, Object with) {
        if(expected.get(messageName) != null){
            expected.get(messageName).arrived(messageName, with, null);
        }
    }

    public MockNetwork getOpponent() {
        return opponent;
    }

    public void setOpponent(MockNetwork opponent) {
        this.opponent = opponent;
    }
}
