package tetrispvp.board;


import com.google.inject.Inject;
import GUI.Block.Block;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class NormalCollisionChecker implements CollisionChecker {

    private Board board;

    @Inject
    public NormalCollisionChecker(Board board) {
        this.board = board;
    }


    private boolean columnWithinBounds(int column) {
        return column >= 0 && column < board.getWidth();
    }

    private boolean rowWithinBounds(int row) {
        //row below zero could be valid for a newly spawned block (it's above the displayed board).
        return row < board.getHeight();
    }

    @Override
    public boolean positionWithinBounds(int column, int row) {
        return columnWithinBounds(column) && rowWithinBounds(row);
    }

    public boolean positionWithinBounds(Point point) {
        return positionWithinBounds(point.x, point.y);
    }

    @Override
    public boolean collides(Point position, Block block, List<Point> collisionPoints) {
        List<List<GridField>> blockFields = block.getBoardFields();
        List<List<GridField>> boardFields = board.getBoardState();
        if (collisionPoints == null) {
            collisionPoints = new ArrayList<Point>();
        }
        for (int i = 0; i < blockFields.size(); ++i) {
            for (int j = 0; j < blockFields.get(i).size(); j++) {
                GridField currentBlockField = blockFields.get(i).get(j);
                if (currentBlockField.isOccupied()) {
                    int row = i + position.y;
                    int column = j + position.x;
                    Point currentPoint = new Point(column, row);
                    if (!positionWithinBounds(column, row)) {
                        //collision with bounds
                        collisionPoints.add(currentPoint);
                    } else if (row >= 0) {
                        GridField boardField = boardFields.get(row).get(column);
                        if (boardField.isOccupied() && boardField.isLocked()) {
                            //collision with another field
                            collisionPoints.add(currentPoint);
                        }
                    }

                }
            }
        }
        return collisionPoints.size() > 0;
    }

    @Override
    public boolean collides(Point position, Block block) {
        return collides(position, block, null);
    }
}
