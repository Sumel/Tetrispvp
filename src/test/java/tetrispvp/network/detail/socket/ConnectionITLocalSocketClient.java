package tetrispvp.network.detail.socket;

import tetrispvp.network.helper.ConnectionITClientHelper;

public class ConnectionITLocalSocketClient {

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 52869;

        String address = host + ":" + port;
        System.out.println("Connecting to " + address);

        new ConnectionITClientHelper(address).run();
    }

}
