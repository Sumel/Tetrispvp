package tetrispvp.board;

public interface MutableBoard extends Board {
    void flipBoard();

    void addLine(int lineNumber, GridField field);

    void clearLine(int lineNumber);

    void setFieldAtPosition(GridField field, int x, int y);
}
