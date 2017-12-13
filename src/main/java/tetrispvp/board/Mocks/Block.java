package tetrispvp.board.Mocks;

import tetrispvp.board.*;

import java.util.List;

public interface Block {
    GridField getFieldAtPosition(int x, int y);
    List<List<GridField>> getBoardFields();
    void rotateClockwise();
    void rotateCounterClockwise();
}
