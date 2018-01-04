package tetrispvp.board;


import tetrispvp.board.Mocks.Block;

import java.util.List;

public class TetrisBoard implements BlockMover, MutableBoard {

    private BlockMover blockMover = new DTETBlockMover(this);
    private final static int boardWidth = 10;
    private final static int boardHeight = 20;

    @Override
    public void spawnNewBlock(Block newBlock) {

    }

    @Override
    public boolean moveDown() {
        return false;
    }

    @Override
    public boolean moveLeft() {
        return false;
    }

    @Override
    public boolean moveRight() {
        return false;
    }

    @Override
    public boolean moveToBottom() {
        return false;
    }

    @Override
    public boolean rotateClockwise() {
        return false;
    }

    @Override
    public boolean rotateCounterClockwise() {
        return false;
    }

    @Override
    public boolean isBlockCollidingBelow() {
        return false;
    }

    @Override
    public void addBlockCollidedBelowListener(BlockCollidedBelowListener newListener) {

    }

    @Override
    public void flipBoard() {

    }

    @Override
    public void addLine(int lineNumber, GridField field) {

    }

    @Override
    public void clearLine(int lineNumber) {

    }

    @Override
    public void setFieldAtPosition(GridField field, int x, int y) {

    }

    @Override
    public GridField getFieldAtPosition(int x, int y) {
        return null;
    }

    @Override
    public List<List<GridField>> getBoardState() {
        return null;
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

    }

    @Override
    public void addLinesClearedListener(LinesClearedListener newListener) {

    }
}
