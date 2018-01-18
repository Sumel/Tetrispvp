package tetrispvp.powerUp;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import powerUps.*;
import powerUps.mocks.FieldState;
import powerUps.mocks.GridField;
import powerUps.mocks.MockHandler;
import powerUps.mocks.MockNetwork;
import powerUps.mocks.MutableBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

public class PowerUpManagerTest {
	BoardForTests boardForTests = new BoardForTests();
	
	 private PowerUpManager getOpponentPowerUpManager(){
	        PowerUpManager powerUpManager = mock(PowerUpManager.class);
	        MutableBoard board = boardForTests.getBoard();
	        when(powerUpManager.getMockNetwork()).thenReturn(new MockNetwork());
	        when(powerUpManager.getBoard()).thenReturn(board);

	        powerUpManager.getMockNetwork().expect("addLines", new MockHandler(board));
	        powerUpManager.getMockNetwork().expect("flipBoard", new MockHandler(board));

	        return powerUpManager;
	    }
	
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
    public void checkForPowerUpsNoPowerUps(){
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
    public void checkForPowerUpsRandomPowerUps(){
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
    public void checkForPowerUpsDifferentPowerUps(){
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
    
    @Test
    public void lineClearedTest(){
    	MutableBoard board = boardForTests.getBoard();
        PowerUpManager opponent = getOpponentPowerUpManager();
        MockNetwork mockNetwork = new MockNetwork(opponent.getMockNetwork());

        PowerUpManager.getPowerUpManager().setBoard(board);
        PowerUpManager.getPowerUpManager().setMockNetwork(mockNetwork);
    	List<GridField> line = new ArrayList<GridField>();
    	GridField gf = new GridField();
    	gf.setPowerUp(0);
    	line.add(gf);
    	for(int i = 1; i < 10; i++){
    		GridField gf1 = new GridField();
    		line.add(gf1);
    	}
    	boardForTests.printBoard(opponent.getBoard());
        PowerUpManager.getPowerUpManager().lineCleared(line);
        System.out.println();
        boardForTests.printBoard(opponent.getBoard());
        List<GridField> testedLineBlocked1 = opponent.getBoard().getBoard().get(board.getHeight() - 1);
        List<GridField> testedLineOccupied1 = opponent.getBoard().getBoard().get(board.getHeight() - 2);
        List<GridField> testedLineOccupied2 = opponent.getBoard().getBoard().get(board.getHeight() - 3);
        List<GridField> testedLineEmpty1 = opponent.getBoard().getBoard().get(0);

        for(int i = 0; i < opponent.getBoard().getWidth(); i++){
            assertEquals(testedLineEmpty1.get(i).getState(), FieldState.EMPTY);
            assertEquals(testedLineBlocked1.get(i).getState(), FieldState.BLOCKED);
            assertEquals(testedLineOccupied1.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied2.get(i).getState(), FieldState.OCCUPIED);
        }
    }
}
