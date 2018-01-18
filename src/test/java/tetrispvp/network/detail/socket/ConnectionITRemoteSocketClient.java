package tetrispvp.network.detail.socket;

import tetrispvp.network.helper.ConnectionITClientHelper;

import java.util.Scanner;

public class ConnectionITRemoteSocketClient {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Host: ");
        String host = input.nextLine();
        int port = 52869;

        String address = host + ":" + port;
        System.out.println("Connecting to " + address);

        new ConnectionITClientHelper(address).run();
    }

}
