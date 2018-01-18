package GUI.Mocks;

import static java.lang.Thread.sleep;

public class Network {
    public String getIp(){
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "12.2.89.49";
    }
}
