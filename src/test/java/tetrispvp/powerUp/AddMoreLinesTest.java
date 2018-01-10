package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.AddMoreLines;
import powerUps.PowerUpManager;
import powerUps.mocks.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddMoreLinesTest {
    private BoardForTests boardForTests = new BoardForTests();

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
    public void getSameAddMoreLinesInstance(){
        AddMoreLines addMoreLines1 = AddMoreLines.getAddMoreLines();
        AddMoreLines addMoreLines2 = AddMoreLines.getAddMoreLines();

        assertEquals(addMoreLines1, addMoreLines2);
    }

    @Test
    public void addOneMoreLine(){
        MutableBoard board = boardForTests.getBoard();
        PowerUpManager opponent = getOpponentPowerUpManager();
        MockNetwork mockNetwork = new MockNetwork(opponent.getMockNetwork());

        PowerUpManager.getPowerUpManager().setBoard(board);
        PowerUpManager.getPowerUpManager().setMockNetwork(mockNetwork);

        boardForTests.printBoard(opponent.getBoard());

        AddMoreLines addMoreLines = AddMoreLines.getAddMoreLines();
        addMoreLines.activate(1);

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

    public void addTwoMoreLine(){

    }

    public void addMoreThanTwoMoreLine(){

    }
}
