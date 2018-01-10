package tetrispvp.board;

public interface BlockMover {
    void spawnNewBlock(tetrispvp.board.Mocks.Block newBlock);

    boolean moveDown();

    boolean moveLeft();

    boolean moveRight();

    boolean moveToBottom();

    boolean rotateClockwise();

    boolean rotateCounterClockwise();

    boolean isBlockCollidingBelow();

    void addBlockCollidedBelowListener(BlockCollidedBelowListener newListener);

    void addBlockSpawnedListener(BlockSpawnedListener newListener);
}
