package tetrispvp.board;


import tetrispvp.board.Mocks.Block;

import java.awt.*;
import java.util.*;

public interface CollisionChecker {
    boolean positionWithinBounds(int column, int row);

    boolean collides(Point position, Block block, java.util.List<Point> collisionPoints);

    boolean collides(Point position, Block block);
}
