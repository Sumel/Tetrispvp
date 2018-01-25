package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.ClearBottomLine;
import powerUps.PowerUpManager;
import powerUps.mocks.FieldState;
import powerUps.mocks.GridField;
import powerUps.mocks.MutableBoard;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClearBottomLineTest {
    private MutableBoard getBoard(){
        MutableBoard board = new MutableBoard();
        List<List<GridField>> b = new ArrayList<>();
        List<GridField> line = new ArrayList<>();

        for (int j = 0; j < board.getWidth(); j++) {
            GridField gf = new GridField();
            gf.setState(FieldState.OCCUPIED);
            line.add(gf);
        }
        b.add(line);

        for(int i = 1; i < board.getHeight() - 3; i++){
            line = new ArrayList<>();
            for(int j = 0; j < board.getWidth(); j++){
                line.add(new GridField());
            }
            b.add(line);
        }

        line = new ArrayList<>();
        for(int i = board.getHeight() - 3; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                GridField gf = new GridField();
                gf.setState(FieldState.OCCUPIED);
                line.add(gf);
            }
            b.add(line);
        }
        board.setBoard(b);
        printBoard(board);

        return board;
    }

    private List<GridField> getLine(FieldState state, int size){
        List<GridField> line = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            GridField gf = new GridField();
            gf.setState(state);
            line.add(gf);
        }

        return line;
    }

    private void printBoard(MutableBoard board){
        for(int i = 0; i < board.getHeight(); i++){
            for (int j = 0; j < board.getWidth(); j++){
                List<GridField> temp = board.getBoard().get(i);
                System.out.print(temp.get(j).getState() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void getSameClearBottomLineInstance(){
        ClearBottomLine clearBottomLine1 = ClearBottomLine.getClearBottomLine();
        ClearBottomLine clearBottomLine2 = ClearBottomLine.getClearBottomLine();

        assertEquals(clearBottomLine1, clearBottomLine2);
    }

    @Test
    public void clearOneLine(){
        MutableBoard board = getBoard();
        PowerUpManager.getPowerUpManager().setBoard(board);
        ClearBottomLine clearBottomLine = ClearBottomLine.getClearBottomLine();

        clearBottomLine.activate(1);

        System.out.println();
        printBoard(board);

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
        MutableBoard board = getBoard();
        PowerUpManager.getPowerUpManager().setBoard(board);
        ClearBottomLine clearBottomLine = ClearBottomLine.getClearBottomLine();

        clearBottomLine.activate(2);

        System.out.println();
        printBoard(board);

        List<GridField> testedLineOccupied1 = board.getBoard().get(2);
        List<GridField> testedLineOccupied2 = board.getBoard().get(board.getHeight() - 1);
        List<GridField> testedLineEmpty2 = board.getBoard().get(board.getHeight() - 2);
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
        MutableBoard board = getBoard();
        PowerUpManager.getPowerUpManager().setBoard(board);
        ClearBottomLine clearBottomLine = ClearBottomLine.getClearBottomLine();

        clearBottomLine.activate(4);

        System.out.println();
        printBoard(board);

        List<GridField> testedLineOccupied1 = board.getBoard().get(2);
        List<GridField> testedLineOccupied2 = board.getBoard().get(board.getHeight() - 1);
        List<GridField> testedLineEmpty2 = board.getBoard().get(board.getHeight() - 2);
        List<GridField> testedLineEmpty1 = board.getBoard().get(0);

        for(int i = 0; i < board.getWidth(); i++){
            assertEquals(testedLineEmpty1.get(i).getState(), FieldState.EMPTY);
            assertEquals(testedLineEmpty2.get(i).getState(), FieldState.EMPTY);
            assertEquals(testedLineOccupied1.get(i).getState(), FieldState.OCCUPIED);
            assertEquals(testedLineOccupied2.get(i).getState(), FieldState.OCCUPIED);
        }
    }
}
