package tetrispvp.network.detail;

import tetrispvp.network.helper.ConnectionITClientHelper;
import tetrispvp.network.helper.ModuleITClientHelper;
import tetrispvp.network.helper.ModuleITServerHelper;

public class ModuleITLocalSocketClient {

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 52869;

        String address = host + ":" + port;
        System.out.println("Connecting to " + address);

        new ModuleITClientHelper(address).run();
    }

}
