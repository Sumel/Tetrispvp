package powerUps;

import java.util.List;

import powerUps.mocks.GridField;

public class PowerUpManager {
    private static PowerUpManager powerUpManager = null;

    private PowerUpManager() {
    }

    public static PowerUpManager getPowerUpManager(){
        if(powerUpManager == null){
            powerUpManager = new PowerUpManager();
        }
        return powerUpManager;
    }

    public PowerUp randomPowerUp(){
        //TODO: implement
	    return null;
	}

	public void checkForPowerUps(List<GridField> lines){
        //TODO: implement
    }

}
