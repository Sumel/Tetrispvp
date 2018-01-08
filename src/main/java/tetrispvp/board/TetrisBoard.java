package tetrispvp.board;


import javafx.scene.chart.LineChart;
import tetrispvp.board.Mocks.Block;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.google.inject.*;

public class TetrisBoard implements BlockMover, MutableBoard {
    
    private BlockMover blockMover;
    private final static int boardWidth = 10;
    private final static int boardHeight = 20;
    private List<List<GridField>> boardFields;
    private List<BoardStateChangedListener> stateChangedListeners = new LinkedList<BoardStateChangedListener>();
    private List<LinesClearedListener> lineClearedListeners = new LinkedList<LinesClearedListener>();

    @Inject
    public TetrisBoard(BlockMover blockMover) {
        boardFields = new ArrayList<List<GridField>>();
        for (int i = 0; i < boardHeight; ++i) {
            boardFields.add(createEmptyRow());
        }
        this.blockMover = blockMover;
    }

    private List<GridField> createEmptyRow() {
        GridField emptyField = BoardField.GetEmptyBoardField();
        List<GridField> row = new ArrayList<GridField>();
        for (int j = 0; j < boardWidth; ++j) {
            row.add(emptyField);
        }
        return row;
    }

    @Override
    public void spawnNewBlock(Block newBlock) {
        blockMover.spawnNewBlock(newBlock);
    }

    @Override
    public boolean moveDown() {
        return blockMover.moveDown();
    }

    @Override
    public boolean moveLeft() {
        return blockMover.moveLeft();
    }

    @Override
    public boolean moveRight() {
        return blockMover.moveRight();
    }

    @Override
    public boolean moveToBottom() {
        return blockMover.moveToBottom();
    }

    @Override
    public boolean rotateClockwise() {
        return blockMover.rotateClockwise();
    }

    @Override
    public boolean rotateCounterClockwise() {
        return blockMover.rotateCounterClockwise();
    }

    @Override
    public boolean isBlockCollidingBelow() {
        return blockMover.isBlockCollidingBelow();
    }

    @Override
    public void addBlockCollidedBelowListener(BlockCollidedBelowListener newListener) {
        blockMover.addBlockCollidedBelowListener(newListener);
    }

    @Override
    public void flipBoard() {
        //TODO
    }

    @Override
    public void addLine(int lineNumber, GridField field) {

    }

    @Override
    public void clearLine(int lineNumber) {
        clearLine(lineNumber, true);
    }

    private LinesClearedListener.Line clearLine(int lineNumber, boolean fireEvents) {
        //saves cleared fields
        List<GridField> savedFields = new ArrayList<GridField>();
        for (int i = 0; i < boardWidth; ++i) {
            savedFields.add(boardFields.get(lineNumber).get(i));
        }
        //shift rows by one
        for (int i = lineNumber; i > 0; --i) {
            boardFields.set(i, boardFields.get(i - 1));
        }
        boardFields.set(0, createEmptyRow());
        LinesClearedListener.Line ret = new BoardLine(lineNumber, savedFields);

        if (fireEvents) {
            onBoardChanged();
            List<LinesClearedListener.Line> lines = new ArrayList<LinesClearedListener.Line>();
            lines.add(ret);
            onLinesCleared(lines);
        }

        return ret;
    }

    @Override
    public void setFieldAtPosition(GridField field, int x, int y) {
        setFieldAtPosition(field, x, y, true, true);
        onBoardChanged();
    }

    private boolean positionInDisplayBoundaries(int col, int row) {
        return col >= 0 && col < getWidth() && row >= 0 && row < getHeight();
    }

    private void setFieldAtPosition(GridField field, int col, int row, boolean fireEvent, boolean checkLinesCleared) {
        if (!positionInDisplayBoundaries(col, row)) {
            return;
        }
        GridField currentField = boardFields.get(row).get(col);
        if (currentField.equals(field)) {
            return;
        }
        boardFields.get(row).set(col, field);
        if (fireEvent) {
            onBoardChanged();
        }
        if (checkLinesCleared) {
            checkAndClearLines();
        }
    }

    @Override
    public void setFields(List<GridFieldWithPosition> fields) {
        for (GridFieldWithPosition field : fields) {
            setFieldAtPosition(field.getGridField(), field.getColumnNumber(), field.getRowNumber(), false, false);
        }
        checkAndClearLines();
        onBoardChanged();
    }

    private void checkAndClearLines() {
        List<Integer> rowsToClear = new LinkedList<Integer>();
        for (int row = 0; row < boardHeight; ++row) {
            boolean shouldBeCleared = true;
            for (int col = 0; col < boardWidth; ++col) {
                GridField currentField = boardFields.get(row).get(col);
                if (!currentField.isLocked() || !currentField.isOccupied() || !currentField.canBeCleared()) {
                    shouldBeCleared = false;
                    break;
                }
            }
            if (shouldBeCleared) {
                rowsToClear.add(row);
            }
        }
        List<LinesClearedListener.Line> lines = new ArrayList<LinesClearedListener.Line>();
        for (Integer i : rowsToClear) {
            lines.add(clearLine(i, false));
        }
        onLinesCleared(lines);

    }

    @Override
    public GridField getFieldAtPosition(int x, int y) {
        return boardFields.get(y).get(x);
    }

    @Override
    public List<List<GridField>> getBoardState() {
        //TODO: make immutable
        return boardFields;
    }

    @Override
    public int getWidth() {
        return boardWidth;
    }

    @Override
    public int getHeight() {
        return boardHeight;
    }

    @Override
    public void addBoardStateChangedListener(BoardStateChangedListener newListener) {
        stateChangedListeners.add(newListener);
    }

    @Override
    public void addLinesClearedListener(LinesClearedListener newListener) {
        lineClearedListeners.add(newListener);
    }

    private void onBoardChanged() {
        for (BoardStateChangedListener listener : stateChangedListeners) {
            listener.stateChanged();
        }
    }

    private void onLinesCleared(List<LinesClearedListener.Line> clearedLines) {
        for (LinesClearedListener listener : lineClearedListeners) {
            listener.linesCleared(clearedLines);
        }
    }
}
