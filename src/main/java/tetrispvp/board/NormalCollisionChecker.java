package tetrispvp.board;


import tetrispvp.board.Mocks.Block;

import java.awt.Point;
import java.util.List;

public class NormalCollisionChecker implements CollisionChecker {

    private Board board;

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
    public boolean collides(Point position, Block block) {
        List<List<GridField>> blockFields = block.getBoardFields();
        List<List<GridField>> boardFields = board.getBoardState();
        for (int i = 0; i < blockFields.size(); ++i) {
            for (int j = 0; j < blockFields.get(i).size(); j++) {
                GridField currentBlockField = blockFields.get(i).get(j);
                if (currentBlockField.isOccupied()) {
                    int row = i + position.y;
                    int column = j + position.x;
                    if (!positionWithinBounds(column, row)){
                        return true;
                    }
                    if(row >= 0){
                        GridField boardField = boardFields.get(row).get(column);
                        if (boardField.isOccupied() && boardField.isLocked()) {
                            //collision
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }
}
