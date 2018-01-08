package tetrispvp.board;

import java.util.List;

public interface MutableBoard extends Board {
    void flipBoard();

    void addLine(int lineNumber, GridField field, boolean moveUp);

    void clearLine(int lineNumber);

    void setFieldAtPosition(GridField field, int x, int y);

    void setFields(List<GridFieldWithPosition> fields);
}
