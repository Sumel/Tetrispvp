package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.PowerUpManager;
import powerUps.PowerUpTypes;
import powerUps.mocks.GridField;
import powerUps.mocks.MockNetwork;
import powerUps.mocks.MutableBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    
    @Test
    public void checkForPowerUps_NoPowerUps(){
    	List<GridField> line = new ArrayList<GridField>();
    	for(int i = 0; i < 10; i++){
    		GridField gf = mock(GridField.class);
    		line.add(gf);
    	}
    	int[] powerUpsPresence = PowerUpManager.getPowerUpManager().checkForPowerUps(line);
    	for(int i = 0; i < powerUpsPresence.length; i++){
    		assertEquals(0, powerUpsPresence[i]);
    	}
    }
    
    @Test
    public void checkForPowerUps_RandomPowerUps(){
    	List<GridField> line = new ArrayList<GridField>();
    	Random random = new Random();
    	int powerUpsN = 0;
    	for(int i = 0; i < 10; i++){
    		GridField gf = new GridField();
    		if(random.nextInt() % 2 == 0){
    			gf.setPowerUp(PowerUpManager.getPowerUpManager().randomPowerUp());
    			powerUpsN++;
    		}
    		line.add(gf);
    	}
    	int[] powerUpsPresence = PowerUpManager.getPowerUpManager().checkForPowerUps(line);
    	int powerUpsSum = 0;
    	for(int i = 0; i < powerUpsPresence.length; i++){
    		powerUpsSum += powerUpsPresence[i];
    	}
    	assertEquals(powerUpsN, powerUpsSum);
    }
    
    @Test
    public void checkForPowerUps_DifferentPowerUps(){
    	List<GridField> line = new ArrayList<GridField>();
    	int[] powerUpsPresence = {0, 3, 2, 3};
    	GridField gf = new GridField();
    	line.add(gf);
    	for(int i = 1; i < 20; i++){
    		GridField gf1 = new GridField();
    		if(i % 6 == 0){
    			gf1.setPowerUp(1);
    		}
    		else if(i % 8 == 0){
    			gf1.setPowerUp(2);
    		}
    		else if(i % 5 == 0){
    			gf1.setPowerUp(3);
    		}
    		line.add(gf1);
    	}
    	int[] powerUpsPresenceTest = PowerUpManager.getPowerUpManager().checkForPowerUps(line);
    	for(int i = 0; i < powerUpsPresence.length; i++){
    		assertEquals(powerUpsPresence[i], powerUpsPresenceTest[i]);
    	}
    }
    
    @Test
    public void isListener(){
    	MutableBoard board = new MutableBoard();
    	PowerUpManager p = PowerUpManager.getPowerUpManager();
    	p.setBoard(board);
    	assertEquals(board.lineClearedListenersSize(), 1);
    }
}
