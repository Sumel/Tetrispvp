package tetrispvp.board.Mocks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NetworkModuleMock implements MessageReceiver, MessageSender {
    private Map<String, List<MessageHandler>> handlers = new HashMap<>();
    private NetworkModuleMock otherNetwork;


    @Override
    public void send(String messageName, Object with) {
        otherNetwork.messageFromOtherNetwork(messageName, with);
    }

    @Override
    public void expect(String messageName, MessageHandler handler) {
        if (!handlers.containsKey(messageName)) {
            handlers.put(messageName, new LinkedList<>());
        }
        handlers.get(messageName).add(handler);
    }

    public void messageFromOtherNetwork(String messageName, Object with) {
        for (MessageHandler mHandler : handlers.get(messageName)) {
            mHandler.arrived(messageName, with, null);
        }
    }

    public NetworkModuleMock getOtherNetwork() {
        return otherNetwork;
    }

    public void setOtherNetwork(NetworkModuleMock otherNetwork) {
        this.otherNetwork = otherNetwork;
    }
}
