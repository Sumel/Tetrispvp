package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.AddMoreLines;
import powerUps.PowerUpManager;
import powerUps.mocks.MockNetwork;
import powerUps.mocks.MutableBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class AddMoreLinesTest {
    private BoardForTests boardForTests = new BoardForTests();

    @Test
    public void getSameAddMoreLinesInstance(){
        AddMoreLines addMoreLines1 = AddMoreLines.getAddMoreLines();
        AddMoreLines addMoreLines2 = AddMoreLines.getAddMoreLines();

        assertEquals(addMoreLines1, addMoreLines2);
    }

    @Test
    public void addOneMoreLine(){
        MutableBoard board = boardForTests.getBoard();
        //MockNetwork opponent = mock(MockNetwork.class);
        //MockNetwork mockNetwork = new MockNetwork(opponent);

        PowerUpManager.getPowerUpManager().setBoard(board);
        //PowerUpManager.getPowerUpManager().setMockNetwork(mockNetwork);

        AddMoreLines addMoreLines = AddMoreLines.getAddMoreLines();
        addMoreLines.activate(3);

        System.out.println();
        boardForTests.printBoard(board);

        assertTrue(false);
    }
}
