package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.PowerUpManager;
import powerUps.PowerUpTypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PowerUpManagerTest {
    @Test
    public void incorrectPowerUp(){
    	int powerUp = PowerUpManager.getPowerUpManager().randomPowerUp();
    	assertTrue(powerUp >= 0 && powerUp < PowerUpTypes.values().length);
    }

    @Test
    public void getSamePowerUpManager(){
        PowerUpManager p1 = PowerUpManager.getPowerUpManager();
        PowerUpManager p2 = PowerUpManager.getPowerUpManager();

        assertEquals(p1, p2);
    }
}
