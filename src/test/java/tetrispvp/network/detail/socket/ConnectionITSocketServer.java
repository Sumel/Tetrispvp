package tetrispvp.network.detail.socket;

import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.helper.ConnectionITServerHelper;

public class ConnectionITSocketServer {

    public static void main(String[] args) {
        SocketConnectionProvider provider = (SocketConnectionProvider)
                ConnectionFactory.getProvider();
        provider.overrideListenPort(52869);

        new ConnectionITServerHelper().run();
    }
}
