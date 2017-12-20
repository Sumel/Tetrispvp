package tetrispvp.board;

import java.util.List;

public interface Board {
    GridField getFieldAtPosition(int x, int y);

    List<List<GridField>> getBoardState();

    void addBoardStateChangedListener(BoardStateChangedListener newListener);

    void addLinesClearedListener(LinesClearedListener newListener);
}
