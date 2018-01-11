package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.ClearBottomLine;
import powerUps.PowerUpManager;
import powerUps.mocks.FieldState;
import powerUps.mocks.GridField;
import powerUps.mocks.MutableBoard;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClearBottomLineTest {
    BoardForTests boardForTests = new BoardForTests();
    @Test
    public void getSameClearBottomLineInstance(){
        ClearBottomLine clearBottomLine1 = ClearBottomLine.getClearBottomLine();
        ClearBottomLine clearBottomLine2 = ClearBottomLine.getClearBottomLine();

        assertEquals(clearBottomLine1, clearBottomLine2);
    }

    @Test
    public void clearOneLine(){
        MutableBoard board = boardForTests.getBoard();
        PowerUpManager.getPowerUpManager().setBoard(board);
        ClearBottomLine clearBottomLine = ClearBottomLine.getClearBottomLine();

        clearBottomLine.activate(1);

        System.out.println();
        System.out.println("Lines = 1");
        boardForTests.printBoard(board);

        List<GridField> testedLineOccupied1 = board.getBoard().get(1);
        List<GridField> testedLineOccupied2 = board.getBoard().get(board.getHeight() - 1);
        List<GridField> testedLineOccupied3 = board.getBoard().get(board.getHeight() - 2);
        List<GridField> testedLineEmpty1 = board.getBoard().get(0);

        for(int i = 0; i < board.getWidth(); i++){
            assertEquals(testedLineEmpty1.get(i).getState(), FieldState.EMPTY);
            assertEquals(testedLineOccupied1.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied2.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied3.get(i).getState(), FieldState.OCCUPIED);
        }
    }

    @Test
    public void clearTwoLines(){
        MutableBoard board = boardForTests.getBoard();
        PowerUpManager.getPowerUpManager().setBoard(board);
        ClearBottomLine clearBottomLine = ClearBottomLine.getClearBottomLine();

        clearBottomLine.activate(2);

        System.out.println();
        System.out.println("Lines = 2");
        boardForTests.printBoard(board);

        List<GridField> testedLineOccupied1 = board.getBoard().get(2);
        List<GridField> testedLineOccupied2 = board.getBoard().get(board.getHeight() - 1);
        List<GridField> testedLineEmpty2 = board.getBoard().get(board.getHeight() - 3);
        List<GridField> testedLineEmpty1 = board.getBoard().get(0);

        for(int i = 0; i < board.getWidth(); i++){
            assertEquals(testedLineEmpty1.get(i).getState(), FieldState.EMPTY);
            assertEquals(testedLineOccupied1.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied2.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineEmpty2.get(i).getState(), FieldState.EMPTY);
        }
    }

    @Test
    public void clearMoreThanTwoLines(){
        MutableBoard board = boardForTests.getBoard();
        PowerUpManager.getPowerUpManager().setBoard(board);
        ClearBottomLine clearBottomLine = ClearBottomLine.getClearBottomLine();

        clearBottomLine.activate(4);

        System.out.println();
        System.out.println("Lines = 4");
        boardForTests.printBoard(board);

        List<GridField> testedLineOccupied1 = board.getBoard().get(2);
        List<GridField> testedLineOccupied2 = board.getBoard().get(board.getHeight() - 1);
        List<GridField> testedLineEmpty2 = board.getBoard().get(board.getHeight() - 3);
        List<GridField> testedLineEmpty1 = board.getBoard().get(0);

        for(int i = 0; i < board.getWidth(); i++){
            assertEquals(testedLineEmpty1.get(i).getState(), FieldState.EMPTY);
            assertEquals(testedLineEmpty2.get(i).getState(), FieldState.EMPTY);
            assertEquals(testedLineOccupied1.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied2.get(i).getState(), FieldState.OCCUPIED);
        }
    }
}
