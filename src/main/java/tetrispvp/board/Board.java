package tetrispvp.board;

import java.util.List;

public interface Board {
    BoardField getFieldAtPosition(int x, int y);
    List<List<BoardField>> getBoardState();
    void addBoardStateChangedListener(BoardStateChangedListener newListener);
    void addLineClearedListener(LineClearedListener newListener);
}
