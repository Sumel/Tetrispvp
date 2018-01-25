package tetrispvp.network.helper;

import tetrispvp.network.detail.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class LocalConnectionIT {
    public static void main(String[] args) {

        List<Thread> threadList = new ArrayList<>();
        threadList.add(new Thread(() -> {

            System.out.println("Starting server");
            ConnectionFactory.overrideProvider(new LocalConnectionProvider());

            new ConnectionITServerHelper().run();
        }));

        threadList.add(new Thread(() -> {

            System.out.println("Starting client");
            String host = "127.0.0.1";
            int port = 52869;

            String address = host + ":" + port;
            System.out.println("Connecting to " + address);

            new ConnectionITClientHelper(address).run();
        }));

        threadList.get(0).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadList.get(1).start();

        threadList.forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
