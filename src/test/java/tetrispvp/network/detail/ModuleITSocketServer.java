package tetrispvp.network.detail;

import tetrispvp.network.detail.socket.SocketConnectionProvider;
import tetrispvp.network.helper.ConnectionITServerHelper;
import tetrispvp.network.helper.ModuleITServerHelper;

public class ModuleITSocketServer {

    public static void main(String[] args) {
        SocketConnectionProvider provider = (SocketConnectionProvider)
                ConnectionFactory.getProvider();
        provider.overrideListenPort(52869);

        new ModuleITServerHelper().run();
    }
}
