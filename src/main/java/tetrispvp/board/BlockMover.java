package tetrispvp.board;

public interface BlockMover {
    void spawnNewBlock(tetrispvp.board.Mocks.Block newBlock);
    void moveDown();
    void moveLeft();
    void moveRight();
    void moveToBottom();
    void rotateClockwise();
    void rotateCounterClockwise();
    boolean isBlockCollidingBelow();
    void addBlockCollidedBelowListener(BlockCollidedBelowListener newListener);
}
