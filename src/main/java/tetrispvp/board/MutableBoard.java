package tetrispvp.board;

public interface MutableBoard extends Board {
    void flipBoard();
    void addLine(int lineNumber, BoardField field);
    void setFieldAtPosition(BoardField field, int x, int y);
}
