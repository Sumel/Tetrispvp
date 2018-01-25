package tetrispvp.board;

import java.awt.*;
import GUI.Block.Block;

public interface BlockMover {
    void spawnNewBlock(Block newBlock);

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

    void removeBlockCollidedBelowListener(BlockCollidedBelowListener listenerToRemove);

    void addBlockSpawnedListener(BlockSpawnedListener newListener);
}
