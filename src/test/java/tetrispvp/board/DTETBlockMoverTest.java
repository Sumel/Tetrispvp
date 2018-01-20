package tetrispvp.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import GUI.Block.Block;
import tetrispvp.board.Mocks.BlockField;
import tetrispvp.board.Mocks.BlockImplementation;
import org.mockito.internal.util.reflection.Whitebox;

import javafx.scene.paint.Color;
import java.awt.Point;
import java.util.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DTETBlockMoverTest {
    @Test
    public void spawnNewBlock() throws Exception {

    }


    @Test
    public void moveDown() throws Exception {
        Point point = new Point(5, 5);
        Block block = new BlockImplementation('I');
        MutableBoard board = TestingUtils.setUpMockBoardSize();
        CollisionChecker checker = mock(CollisionChecker.class);
        when(checker.collides(any(Point.class), any(Block.class))).thenReturn(false);
        DTETBlockMover blockMover = new DTETBlockMover(board, checker);
        List<java.util.List<GridField>> boardState = TestingUtils.getEmptyBoard();
        when(board.getBoardState()).thenReturn(boardState);
        when(board.getFieldAtPosition(anyInt(), anyInt())).thenAnswer(
                new Answer<GridField>() {
                    public GridField answer(InvocationOnMock invocation){
                        Object[] args = invocation.getArguments();
                        int x = (Integer)args[0];
                        int y = (Integer)args[1];
                        return boardState.get(y).get(x);
                    }
                });

                Whitebox.setInternalState(blockMover, "blockPosition", point);
        Whitebox.setInternalState(blockMover, "currentBlock", block);
        blockMover.moveDown();

        ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(board).setFields(argCaptor.capture());
        List<GridFieldWithPosition> capturedArguments = argCaptor.<List<GridFieldWithPosition>>getValue();
        GridField blockField = new BlockField(true, Color.AQUA, -1);
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
        Point point = new Point(5, 5);
        Block block = new BlockImplementation('I');
        MutableBoard board = TestingUtils.setUpMockBoardSize();
        CollisionChecker checker = mock(CollisionChecker.class);
        when(checker.collides(any(Point.class), any(Block.class))).thenReturn(false);
        DTETBlockMover blockMover = new DTETBlockMover(board, checker);
        List<java.util.List<GridField>> boardState = TestingUtils.getEmptyBoard();
        when(board.getBoardState()).thenReturn(boardState);
        when(board.getFieldAtPosition(anyInt(), anyInt())).thenAnswer(
                new Answer<GridField>() {
                    public GridField answer(InvocationOnMock invocation){
                        Object[] args = invocation.getArguments();
                        int x = (Integer)args[0];
                        int y = (Integer)args[1];
                        return boardState.get(y).get(x);
                    }
                });

        Whitebox.setInternalState(blockMover, "blockPosition", point);
        Whitebox.setInternalState(blockMover, "currentBlock", block);
        blockMover.moveLeft();

        ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(board).setFields(argCaptor.capture());
        List<GridFieldWithPosition> capturedArguments = argCaptor.<List<GridFieldWithPosition>>getValue();
        GridField blockField = new BlockField(true, Color.AQUA, -1);
        GridFieldWithPosition[] expectedResult = {
                new GridFieldWithPosition(5, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(6, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(7, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(8, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(5, 5, blockField),
                new GridFieldWithPosition(6, 5, blockField),
                new GridFieldWithPosition(7, 5, blockField),
                new GridFieldWithPosition(8, 5, blockField)
        };
        for (GridFieldWithPosition field : expectedResult) {
            assertTrue(capturedArguments.contains(field));
        }
        assertTrue(expectedResult.length == capturedArguments.size());
    }

    @Test
    public void moveRight() throws Exception {
        Point point = new Point(5, 5);
        Block block = new BlockImplementation('I');
        MutableBoard board = TestingUtils.setUpMockBoardSize();
        CollisionChecker checker = mock(CollisionChecker.class);
        when(checker.collides(any(Point.class), any(Block.class))).thenReturn(false);
        DTETBlockMover blockMover = new DTETBlockMover(board, checker);
        List<java.util.List<GridField>> boardState = TestingUtils.getEmptyBoard();
        when(board.getBoardState()).thenReturn(boardState);
        when(board.getFieldAtPosition(anyInt(), anyInt())).thenAnswer(
                new Answer<GridField>() {
                    public GridField answer(InvocationOnMock invocation){
                        Object[] args = invocation.getArguments();
                        int x = (Integer)args[0];
                        int y = (Integer)args[1];
                        return boardState.get(y).get(x);
                    }
                });

        Whitebox.setInternalState(blockMover, "blockPosition", point);
        Whitebox.setInternalState(blockMover, "currentBlock", block);
        blockMover.moveRight();

        ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(board).setFields(argCaptor.capture());
        List<GridFieldWithPosition> capturedArguments = argCaptor.<List<GridFieldWithPosition>>getValue();
        GridField blockField = new BlockField(true, Color.AQUA, -1);
        GridFieldWithPosition[] expectedResult = {
                new GridFieldWithPosition(5, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(6, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(7, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(8, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(5, 7, blockField),
                new GridFieldWithPosition(6, 7, blockField),
                new GridFieldWithPosition(7, 7, blockField),
                new GridFieldWithPosition(8, 7, blockField)
        };
        for (GridFieldWithPosition field : expectedResult) {
            assertTrue(capturedArguments.contains(field));
        }
        assertTrue(expectedResult.length == capturedArguments.size());
    }

    @Test
    public void moveToBottom() throws Exception {
        Point point = new Point(5, 5);
        Block block = new BlockImplementation('I');
        MutableBoard board = TestingUtils.setUpMockBoardSize();
        CollisionChecker checker = mock(CollisionChecker.class);
        when(checker.collides(argThat(not(new Point(5, 17))), any(Block.class), any(List.class))).thenReturn(false);
        when(checker.collides(eq(new Point(5, 17)), any(Block.class), any(List.class))).thenReturn(true);
        DTETBlockMover blockMover = new DTETBlockMover(board, checker);
        List<java.util.List<GridField>> boardState = TestingUtils.getEmptyBoard();
        when(board.getBoardState()).thenReturn(boardState);
        when(board.getFieldAtPosition(anyInt(), anyInt())).thenAnswer(
                new Answer<GridField>() {
                    public GridField answer(InvocationOnMock invocation){
                        Object[] args = invocation.getArguments();
                        int x = (Integer)args[0];
                        int y = (Integer)args[1];
                        return boardState.get(y).get(x);
                    }
                });

        Whitebox.setInternalState(blockMover, "blockPosition", point);
        Whitebox.setInternalState(blockMover, "currentBlock", block);
        blockMover.moveToBottom();

        ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(board).setFields(argCaptor.capture());
        List<GridFieldWithPosition> capturedArguments = argCaptor.<List<GridFieldWithPosition>>getValue();
        GridField blockField = new BlockField(true, Color.AQUA, -1);
        GridFieldWithPosition[] expectedResult = {
                new GridFieldWithPosition(5, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(6, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(7, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(8, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(16, 6, blockField),
                new GridFieldWithPosition(17, 6, blockField),
                new GridFieldWithPosition(18, 6, blockField),
                new GridFieldWithPosition(19, 6, blockField)
        };
        for (GridFieldWithPosition field : expectedResult) {
            assertTrue(capturedArguments.contains(field));
        }
        assertTrue(expectedResult.length == capturedArguments.size());
    }

    @Test
    public void rotateClockwise() throws Exception {
        Point point = new Point(5, 5);
        Block block = new BlockImplementation('I');
        MutableBoard board = TestingUtils.setUpMockBoardSize();
        CollisionChecker checker = mock(CollisionChecker.class);
        when(checker.collides(any(Point.class), any(Block.class))).thenReturn(false);
        DTETBlockMover blockMover = new DTETBlockMover(board, checker);
        List<java.util.List<GridField>> boardState = TestingUtils.getEmptyBoard();
        when(board.getBoardState()).thenReturn(boardState);
        when(board.getFieldAtPosition(anyInt(), anyInt())).thenAnswer(
                new Answer<GridField>() {
                    public GridField answer(InvocationOnMock invocation){
                        Object[] args = invocation.getArguments();
                        int x = (Integer)args[0];
                        int y = (Integer)args[1];
                        return boardState.get(y).get(x);
                    }
                });

        Whitebox.setInternalState(blockMover, "blockPosition", point);
        Whitebox.setInternalState(blockMover, "currentBlock", block);
        blockMover.rotateClockwise();

        ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(board).setFields(argCaptor.capture());
        List<GridFieldWithPosition> capturedArguments = argCaptor.<List<GridFieldWithPosition>>getValue();
        GridField blockField = new BlockField(true, Color.AQUA, -1);
        GridFieldWithPosition[] expectedResult = {
                new GridFieldWithPosition(5, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(6, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(8, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(7, 5, blockField),
                new GridFieldWithPosition(7, 6, blockField),
                new GridFieldWithPosition(7, 7, blockField),
                new GridFieldWithPosition(7, 8, blockField)
        };
        for (GridFieldWithPosition field : expectedResult) {
            assertTrue(capturedArguments.contains(field));
        }
        assertTrue(expectedResult.length == capturedArguments.size());
    }

    @Test
    public void rotateCounterClockwise() throws Exception {
        Point point = new Point(5, 5);
        Block block = new BlockImplementation('I');
        MutableBoard board = TestingUtils.setUpMockBoardSize();
        CollisionChecker checker = mock(CollisionChecker.class);
        when(checker.collides(any(Point.class), any(Block.class))).thenReturn(false);
        DTETBlockMover blockMover = new DTETBlockMover(board, checker);
        List<java.util.List<GridField>> boardState = TestingUtils.getEmptyBoard();
        when(board.getBoardState()).thenReturn(boardState);
        when(board.getFieldAtPosition(anyInt(), anyInt())).thenAnswer(
                new Answer<GridField>() {
                    public GridField answer(InvocationOnMock invocation){
                        Object[] args = invocation.getArguments();
                        int x = (Integer)args[0];
                        int y = (Integer)args[1];
                        return boardState.get(y).get(x);
                    }
                });

        Whitebox.setInternalState(blockMover, "blockPosition", point);
        Whitebox.setInternalState(blockMover, "currentBlock", block);
        blockMover.rotateCounterClockwise();

        ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(board).setFields(argCaptor.capture());
        List<GridFieldWithPosition> capturedArguments = argCaptor.<List<GridFieldWithPosition>>getValue();
        GridField blockField = new BlockField(true, Color.AQUA, -1);
        GridFieldWithPosition[] expectedResult = {
                new GridFieldWithPosition(5, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(7, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(8, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(6, 5, blockField),
                new GridFieldWithPosition(6, 6, blockField),
                new GridFieldWithPosition(6, 7, blockField),
                new GridFieldWithPosition(6, 8, blockField)
        };
        for (GridFieldWithPosition field : expectedResult) {
            assertTrue(capturedArguments.contains(field));
        }
        assertTrue(expectedResult.length == capturedArguments.size());
    }

    @Test
    public void wallKickTest() throws Exception {
        Point point = new Point(5, 5);
        Block block = new BlockImplementation('I');
        MutableBoard board = TestingUtils.setUpMockBoardSize();
        when(board.getFieldAtPosition(anyInt(), anyInt())).thenAnswer(
                new Answer<GridField>() {
                    public GridField answer(InvocationOnMock invocation){
                        Object[] args = invocation.getArguments();
                        int x = (Integer)args[0];
                        int y = (Integer)args[1];
                        return BoardField.GetEmptyBoardField();
                    }
                });
        CollisionChecker checker = mock(CollisionChecker.class);
        when(checker.collides(argThat(not(new Point(5, 5))), any(Block.class), any(List.class))).thenReturn(false);
        when(checker.collides(eq(new Point(5, 5)), any(Block.class), any(List.class))).thenReturn(true);
        when(checker.collides(argThat(not(new Point(5, 5))), any(Block.class))).thenReturn(false);
        when(checker.collides(eq(new Point(5, 5)), any(Block.class))).thenReturn(true);

        DTETBlockMover blockMover = new DTETBlockMover(board, checker);
        List<java.util.List<GridField>> boardState = TestingUtils.getEmptyBoard();
        when(board.getBoardState()).thenReturn(boardState);

        Whitebox.setInternalState(blockMover, "blockPosition", point);
        Whitebox.setInternalState(blockMover, "currentBlock", block);



        blockMover.rotateClockwise();



        ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(board).setFields(argCaptor.capture());
        List<GridFieldWithPosition> capturedArguments = argCaptor.<List<GridFieldWithPosition>>getValue();
        GridField blockField = new BlockField(true, Color.AQUA, -1);
        GridFieldWithPosition[] expectedResult = {
                new GridFieldWithPosition(5, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(6, 6, BoardField.GetEmptyBoardField()),
                //new GridFieldWithPosition(7, 6, BoardField.GetEmptyBoardField()),
                new GridFieldWithPosition(8, 6, BoardField.GetEmptyBoardField()),

                new GridFieldWithPosition(7, 6, blockField),
                new GridFieldWithPosition(7, 7, blockField),
                new GridFieldWithPosition(7, 8, blockField),
                new GridFieldWithPosition(7, 9, blockField)
        };
        for (GridFieldWithPosition field : expectedResult) {
            assertTrue(capturedArguments.contains(field));
        }
        assertTrue(expectedResult.length == capturedArguments.size());
    }
}