package tetrispvp.board;

import java.awt.geom.Point2D;
import java.util.List;

public interface BlockCollidedBelowListener {
    void BlockCollidedBelow(List<Point2D> collidingPoints);
}
