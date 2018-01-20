package tetrispvp.board;

import org.junit.Test;
import GUI.Block.Block;
import tetrispvp.board.Mocks.BlockImplementation;

import java.awt.Point;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class NormalCollisionCheckerTest {

    @Test
    public void columnsOutsideBounds() throws Exception {
        Board board = TestingUtils.setUpMockBoardSize();
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        assertEquals(checker.positionWithinBounds(13, 2), false);
    }

    @Test
    public void positionWithinBounds() throws Exception {
        Board board = TestingUtils.setUpMockBoardSize();
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        assertEquals(checker.positionWithinBounds(2, 2), true);
    }

    @Test
    public void positionWithinBoundsAboveBoard() throws Exception {
        Board board = TestingUtils.setUpMockBoardSize();
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        assertEquals(checker.positionWithinBounds(0, -5), true);
    }

    @Test
    public void collidesEmptyBoard() throws Exception {
        Board board = TestingUtils.setUpMockBoardSize();
        List<List<GridField>> boardState = TestingUtils.getEmptyBoard();
        when(board.getBoardState()).thenReturn(boardState);
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        Block block = new BlockImplementation('I');
        assertEquals(checker.collides(new Point(5, 5), block), false);
    }

    @Test
    public void collidesTopLeftCorner() throws Exception {
        List<List<GridField>> boardState = TestingUtils.getEmptyBoard();
        BoardField boardField = new BoardField(true, true, Color.RED, -1);
        boardState.get(0).set(0, boardField);

        Board board = TestingUtils.setUpMockBoardSize();
        when(board.getBoardState()).thenReturn(boardState);
        Point currentPosition = new Point(-1,0);
        Block block = new BlockImplementation('I');
        block.rotateCounterClockwise();
        NormalCollisionChecker checker = new NormalCollisionChecker(board);
        assertEquals(checker.collides(currentPosition, block), true);
        currentPosition = new Point(-1, 1);
        assertEquals(checker.collides(currentPosition, block), true);
    }

}