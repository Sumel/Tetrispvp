package controller;

import mocks.PowerUp;
import mocks.PowerUpManager;

import java.util.Random;

public class PowerUpGenerator {

    private static PowerUpGenerator powerUpGenerator;
    private PowerUpManager powerUpManager;
    private Random random = new Random();

    public PowerUpGenerator() {
        this.powerUpManager = PowerUpManager.getPowerUpManager();
    }

    public static PowerUpGenerator getPowerUpGenerator() {
        if(powerUpGenerator == null) {
            powerUpGenerator = new PowerUpGenerator();
        }
        return powerUpGenerator;
    }

    public PowerUp nextRandomPowerUp() {
        return powerUpManager.getPowerUp(powerUpManager.randomPowerUp());
    }

    public int randPowerUpPosition() {
        return random.nextInt(4);
    }

}
