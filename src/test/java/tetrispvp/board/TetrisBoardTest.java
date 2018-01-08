package tetrispvp.board;

import org.junit.Test;
import tetrispvp.board.Mocks.Block;

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
        TetrisBoard board = new TetrisBoard(mover);

        board.spawnNewBlock(block);

        verify(mover, times(1)).spawnNewBlock(block);
    }

    @Test
    public void moveDown() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        TetrisBoard board = new TetrisBoard(mover);

        board.moveDown();

        verify(mover, times(1)).moveDown();
    }

    @Test
    public void moveLeft() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        TetrisBoard board = new TetrisBoard(mover);

        board.moveLeft();

        verify(mover, times(1)).moveLeft();
    }

    @Test
    public void moveRight() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        TetrisBoard board = new TetrisBoard(mover);

        board.moveRight();

        verify(mover, times(1)).moveRight();
    }

    @Test
    public void moveToBottom() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        TetrisBoard board = new TetrisBoard(mover);

        board.moveToBottom();

        verify(mover, times(1)).moveToBottom();
    }

    @Test
    public void rotateClockwise() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        TetrisBoard board = new TetrisBoard(mover);

        board.rotateClockwise();

        verify(mover, times(1)).rotateClockwise();
    }

    @Test
    public void rotateCounterClockwise() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        TetrisBoard board = new TetrisBoard(mover);

        board.rotateCounterClockwise();

        verify(mover, times(1)).rotateCounterClockwise();
    }

    @Test
    public void isBlockCollidingBelow() throws Exception {
        BlockMover mover = mock(BlockMover.class);
        when(mover.isBlockCollidingBelow()).thenReturn(true);
        TetrisBoard board = new TetrisBoard(mover);

        assertTrue(board.isBlockCollidingBelow());

        verify(mover, times(1)).isBlockCollidingBelow();
    }

}