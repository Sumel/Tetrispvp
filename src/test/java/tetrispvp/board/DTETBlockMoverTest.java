package tetrispvp.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import tetrispvp.board.Mocks.Block;
import tetrispvp.board.Mocks.BlockField;
import tetrispvp.board.Mocks.BlockImplementation;
import org.mockito.internal.util.reflection.Whitebox;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;


public class DTETBlockMoverTest {
    @Test
    public void spawnNewBlock() throws Exception {

    }


    @Test
    public void moveDown() throws Exception {
        Point point = new Point(5, 5);
        Block block = new BlockImplementation('I');
        MutableBoard board = TestingUtils.setUpMockBoardSize();
        DTETBlockMover blockMover = new DTETBlockMover(board);
        List<java.util.List<GridField>> boardState = TestingUtils.getEmptyBoard();
        Mockito.when(board.getBoardState()).thenReturn(boardState);

        Whitebox.setInternalState(blockMover, "blockPosition", point);
        Whitebox.setInternalState(blockMover, "currentBlock", block);
        blockMover.moveDown();

        ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(board).setFields(argCaptor.capture());
        List<GridFieldWithPosition> capturedArguments = argCaptor.<List<GridFieldWithPosition>>getValue();
        GridField blockField = new BlockField(true, Color.cyan, -1);
        GridFieldWithPosition[] expectedResult = {
                new GridFieldWithPosition(5, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(6, 6, blockField),
                new GridFieldWithPosition(7, 6, blockField),
                new GridFieldWithPosition(8, 6, blockField),
                new GridFieldWithPosition(9, 6, blockField)
        };
        for (GridFieldWithPosition field : expectedResult) {
            assertTrue(capturedArguments.contains(field));
        }
        assertTrue(expectedResult.length == capturedArguments.size());

    }

    @Test
    public void moveLeft() throws Exception {
    }

    @Test
    public void moveRight() throws Exception {
    }

    @Test
    public void moveToBottom() throws Exception {
    }

    @Test
    public void rotateClockwise() throws Exception {
    }

    @Test
    public void rotateCounterClockwise() throws Exception {
    }

    @Test
    public void isBlockCollidingBelow() throws Exception {
    }

}