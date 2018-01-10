package tetrispvp.board;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import tetrispvp.board.Mocks.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TetrisBoardTest {
    @Test
    public void spawnNewBlock() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        Block block = mock(Block.class);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);

        board.spawnNewBlock(block);

        verify(mover, times(1)).spawnNewBlock(block);
    }

    @Test
    public void moveDown() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);

        board.moveDown();

        verify(mover, times(1)).moveDown();
    }

    @Test
    public void moveLeft() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);

        board.moveLeft();

        verify(mover, times(1)).moveLeft();
    }

    @Test
    public void moveRight() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);

        board.moveRight();

        verify(mover, times(1)).moveRight();
    }

    @Test
    public void moveToBottom() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);

        board.moveToBottom();

        verify(mover, times(1)).moveToBottom();
    }

    @Test
    public void rotateClockwise() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);

        board.rotateClockwise();

        verify(mover, times(1)).rotateClockwise();
    }

    @Test
    public void rotateCounterClockwise() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);

        board.rotateCounterClockwise();

        verify(mover, times(1)).rotateCounterClockwise();
    }

    @Test
    public void isBlockCollidingBelow() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        when(mover.isBlockCollidingBelow()).thenReturn(true);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);

        assertTrue(board.isBlockCollidingBelow());

        verify(mover, times(1)).isBlockCollidingBelow();
    }

    private List<List<GridField>> setUpBoardForFlip() {
        List<List<GridField>> ret = TestingUtils.getEmptyBoard();
        BoardField field = new BoardField(true, true, Color.black, -1);
        ret.get(19).set(4, field);
        ret.get(18).set(4, field);
        ret.get(17).set(4, field);
        ret.get(17).set(5, field);
        return ret;
    }

    @Test
    public void flipTest() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);
        Whitebox.setInternalState(board, "boardFields", setUpBoardForFlip());
        board.flipBoard();
        for (int row = 0; row < board.getHeight(); ++row) {
            for (int col = 0; col < board.getWidth(); ++col) {
                if (    row == 19 && col == 5 ||
                        row == 19 && col == 4 ||
                        row == 18 && col == 4 ||
                        row == 17 && col == 4) {
                    assertEquals(board.getFieldAtPosition(col, row), new BoardField(true, true, Color.black, -1));
                }
                else {
                    assertEquals(board.getFieldAtPosition(col, row), BoardField.GetEmptyBoardField());
                }
            }
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void immutabilityTest() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        GameStateTracker gameStateTracker = mock(GameStateTracker.class);
        TetrisBoard board = new TetrisBoard(mover, gameStateTracker);


        List<List<GridField>> fields = board.getBoardState();
        fields.get(0).set(2,BoardField.GetEmptyBoardField());
        fields.set(3, new ArrayList<GridField>());
    }

}