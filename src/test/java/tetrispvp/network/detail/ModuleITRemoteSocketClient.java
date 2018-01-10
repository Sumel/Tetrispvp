package tetrispvp.network.detail;

import tetrispvp.network.helper.ModuleITClientHelper;

import java.util.Scanner;

public class ModuleITRemoteSocketClient {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Host: ");
        String host = input.nextLine();
        int port = 52869;

        String address = host + ":" + port;
        System.out.println("Connecting to " + address);

        new ModuleITClientHelper(address).run();
    }

}
