package tetrispvp.powerUp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import powerUps.PowerUpManager;
import powerUps.mocks.FieldState;
import powerUps.mocks.GridField;
import powerUps.mocks.LinesClearedListener;
import powerUps.mocks.MockHandler;
import powerUps.mocks.MockNetwork;
import powerUps.mocks.MutableBoard;

public class ModuleTest {
	
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
	public void afterCleaningLine() {
    	MutableBoard board = boardForTests.getBoard();
        PowerUpManager opponent = getOpponentPowerUpManager();
        MockNetwork mockNetwork = new MockNetwork(opponent.getMockNetwork());

        PowerUpManager.getPowerUpManager().setBoard(board);
        LinesClearedListener listener = PowerUpManager.getPowerUpManager().getLinesClearedListener();
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
        listener.lineCleared(line);
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
