package tetrispvp.board;

import org.junit.Test;
import tetrispvp.board.Mocks.Block;
import tetrispvp.board.Mocks.BlockImplementation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class NormalCollisionCheckerTest {


    private Board setUpMockBoardSize() {
        Board board = mock(Board.class);
        when(board.getWidth()).thenReturn(10);
        when(board.getHeight()).thenReturn(20);
        return board;
    }

    @Test
    public void columnsOutsideBounds() throws Exception {
        Board board = setUpMockBoardSize();
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        assertEquals(checker.positionWithinBounds(13, 2), false);
    }

    @Test
    public void positionWithinBounds() throws Exception {
        Board board = setUpMockBoardSize();
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        assertEquals(checker.positionWithinBounds(2, 2), true);
    }

    @Test
    public void positionWithinBoundsAboveBoard() throws Exception {
        Board board = setUpMockBoardSize();
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        assertEquals(checker.positionWithinBounds(0, -5), true);
    }

    private List<List<GridField>> getEmptyBoard() {
        int width = 10;
        int height = 20;

        GridField emptyField = new BoardField();

        List<List<GridField>> board = new ArrayList<List<GridField>>();

        for (int row = 0; row < height; ++row) {
            List<GridField> newRow = new ArrayList<GridField>();
            for (int col = 0; col < width; ++col) {
                newRow.add(col, emptyField);
            }
            board.add(row, newRow);
        }

        return board;
    }

    @Test
    public void collidesEmptyBoard() throws Exception {
        Board board = setUpMockBoardSize();
        List<List<GridField>> boardState = getEmptyBoard();
        when(board.getBoardState()).thenReturn(boardState);
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        Block block = new BlockImplementation('I');
        assertEquals(checker.collides(new Point(5, 5), block), false);
    }

    @Test
    public void collidesTopLeftCorner() throws Exception {
        List<List<GridField>> boardState = getEmptyBoard();
        BoardField boardField = new BoardField(true, true, Color.red, -1);
        boardState.get(0).set(0, boardField);

        Board board = setUpMockBoardSize();
        when(board.getBoardState()).thenReturn(boardState);
        Point currentPosition = new Point(-1,0);
        Block block = new BlockImplementation('I');
        block.rotateCounterClockwise();
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        assertEquals(checker.collides(currentPosition, block), true);
    }

}