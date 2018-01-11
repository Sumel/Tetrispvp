package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.PowerUpManager;
import powerUps.ReverseBoardPowerUp;
import powerUps.mocks.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReverseBoardPowerUpTest {
    private BoardForTests boardForTests = new BoardForTests();

    private PowerUpManager getOpponentPowerUpManager(){
        PowerUpManager powerUpManager = mock(PowerUpManager.class);
        MutableBoard board = boardForTests.getBoardToReverse();
        when(powerUpManager.getMockNetwork()).thenReturn(new MockNetwork());
        when(powerUpManager.getBoard()).thenReturn(board);

        powerUpManager.getMockNetwork().expect("flipBoard", new MockHandler(board));
        powerUpManager.getMockNetwork().expect("addLines", new MockHandler(board));

        return powerUpManager;
    }

    @Test
    public void getSameReverseBoardInstance() {
        ReverseBoardPowerUp reverseBoardPowerUp1 = ReverseBoardPowerUp.getReverseBoardPowerUp();
        ReverseBoardPowerUp reverseBoardPowerUp2 = ReverseBoardPowerUp.getReverseBoardPowerUp();

        assertTrue(reverseBoardPowerUp1 == reverseBoardPowerUp2);
    }

    @Test
    public void reverseBoardOnce(){
        MutableBoard board = boardForTests.getBoard();
        PowerUpManager opponent = getOpponentPowerUpManager();
        MockNetwork mockNetwork = new MockNetwork(opponent.getMockNetwork());

        PowerUpManager.getPowerUpManager().setBoard(board);
        PowerUpManager.getPowerUpManager().setMockNetwork(mockNetwork);
        System.out.println();
        System.out.println("Once");
        boardForTests.printBoard(opponent.getBoard());

        ReverseBoardPowerUp reverseBoardPowerUp = ReverseBoardPowerUp.getReverseBoardPowerUp();
        reverseBoardPowerUp.activate(1);

        System.out.println();
        boardForTests.printBoard(opponent.getBoard());

        
        List<GridField> testedLineEmpty1 = opponent.getBoard().getBoard().get(0);
        List<GridField> testedLineOccupied1 = opponent.getBoard().getBoard().get(board.getHeight() - 4);
        List<GridField> testedLineOccupied2 = opponent.getBoard().getBoard().get(board.getHeight() - 2);
        List<GridField> testedLinePartiallyOccupied1 = opponent.getBoard().getBoard().get(board.getHeight() - 1);

        for(int i = 0; i < opponent.getBoard().getWidth(); i++){
            assertEquals(testedLineEmpty1.get(i).getState(), FieldState.EMPTY);
            if(i %3 == 1)
                assertEquals(testedLinePartiallyOccupied1.get(i).getState(), FieldState.EMPTY);
            else
                assertEquals(testedLinePartiallyOccupied1.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied1.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied2.get(i).getState(), FieldState.OCCUPIED);
        }
    }

    @Test
    public void reverseBoardTwice(){
    	MutableBoard board = boardForTests.getBoard();
        PowerUpManager opponent = getOpponentPowerUpManager();
        MockNetwork mockNetwork = new MockNetwork(opponent.getMockNetwork());

        PowerUpManager.getPowerUpManager().setBoard(board);
        PowerUpManager.getPowerUpManager().setMockNetwork(mockNetwork);
        System.out.println();
        System.out.println("Twice");
        boardForTests.printBoard(opponent.getBoard());
        ReverseBoardPowerUp reverseBoardPowerUp = ReverseBoardPowerUp.getReverseBoardPowerUp();
        reverseBoardPowerUp.activate(2);
        System.out.println();
        boardForTests.printBoard(opponent.getBoard());

        List<GridField> testedLineOccupied1 = opponent.getBoard().getBoard().get(board.getHeight() - 1);
        List<GridField> testedLineEmpty1 = opponent.getBoard().getBoard().get(0);
        List<GridField> testedLineEmpty2 = opponent.getBoard().getBoard().get(board.getHeight() - 5);
        List<GridField> testedLineOccupied2 = opponent.getBoard().getBoard().get(board.getHeight() - 3);
        List<GridField> testedLinePartiallyOccupied1 = opponent.getBoard().getBoard().get(board.getHeight() - 4);

        for(int i = 0; i < opponent.getBoard().getWidth(); i++){
            assertEquals(testedLineEmpty1.get(i).getState(), FieldState.EMPTY);
            assertEquals(testedLineEmpty2.get(i).getState(), FieldState.EMPTY);
            if(i %3 == 1)
                assertEquals(testedLinePartiallyOccupied1.get(i).getState(), FieldState.EMPTY);
            else
                assertEquals(testedLinePartiallyOccupied1.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied1.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied2.get(i).getState(), FieldState.OCCUPIED);
        }
    }
}
