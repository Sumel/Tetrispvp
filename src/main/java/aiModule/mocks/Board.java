package aiModule.mocks;

import java.util.List;

public interface Board {
    GridField getFieldAtPosition(int x, int y);

    List<List<GridField>> getBoardState();

    int getWidth();

    int getHeight();

    boolean insertTetromino(Tetromino t);

    boolean moveDown();

    boolean rotate();

    boolean moveLeft();

    boolean moveRight();

    void totalMoveDown();

}