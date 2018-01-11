package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.PowerUpManager;
import powerUps.PowerUpTypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class PowerUpManagerTest {
    
    @Test
    public void getSamePowerUpManager(){
        PowerUpManager p1 = PowerUpManager.getPowerUpManager();
        PowerUpManager p2 = PowerUpManager.getPowerUpManager();

        assertEquals(p1, p2);
    }
    
    @Test
    public void incorrectPowerUp(){
    	int powerUp = PowerUpManager.getPowerUpManager().randomPowerUp();
    	assertTrue(powerUp >= 0 && powerUp < PowerUpTypes.values().length);
    }

    @Test
    public void differentPowerUps(){
    	PowerUpManager manager = PowerUpManager.getPowerUpManager();
    	int prev = manager.randomPowerUp();
    	int curr;
    	boolean different = false;
    	for(int i = 1; i < 10; i++){
    		curr = manager.randomPowerUp();
    		if(curr != prev){
    			different = true;
    			break;
    		}
    		prev = curr;
    	}
    	assertTrue(different);
    }
}
