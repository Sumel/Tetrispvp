package powerUps.NetworkIntegration;

import powerUps.mocks.MessageReceiver;
import powerUps.mocks.MessageSender;
import powerUps.mocks.MockNetwork;
import tetrispvp.network.NetworkModule;
import tetrispvp.network.NetworkModuleFactory;

public class RealNetwork extends MockNetwork {
    private final NetworkModule network;

    public RealNetwork() {
        this.network = NetworkModuleFactory.last();
    }

    @Override
    public void expect(String messageName, tetrispvp.network.MessageHandler handler) {
        network.messageContext().receiver().expect(messageName, handler);
    }

    @Override
    public void send(String messageName, Object with) {
        network.messageContext().sender().send(messageName, with);
    }
}
