package tetrispvp.board;


import tetrispvp.board.Mocks.Block;

import java.awt.*;

public interface CollisionChecker {
    boolean positionWithinBounds(int column, int row);
    boolean collides(Point position, Block block);
}
