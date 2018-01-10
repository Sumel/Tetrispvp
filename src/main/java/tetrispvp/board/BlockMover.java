package tetrispvp.board;

import java.awt.*;

public interface BlockMover {
    void spawnNewBlock(tetrispvp.board.Mocks.Block newBlock);

    void changePosition(Point point);

    Point getPosition();

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
