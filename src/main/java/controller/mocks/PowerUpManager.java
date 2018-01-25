package controller.mocks;

import java.util.*;

public class PowerUpManager {

    private static PowerUpManager powerUpManager = null;
    private Random random = new Random();
    private List<PowerUp> powerUpList = new ArrayList<>();

    public PowerUpManager() {
        this.powerUpList.add(new PowerUp());
    }


    public static PowerUpManager getPowerUpManager(){
        if(powerUpManager == null){
            powerUpManager = new PowerUpManager();
        }
        return powerUpManager;
    }

    /* returns next random PowerUp Id from the powerUpList */
    public int randomPowerUp() {
        return random.nextInt(powerUpList.size());
    }

    public PowerUp getPowerUp(int powerUpId) {
        return powerUpList.get(powerUpId);
    }

}
