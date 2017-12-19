package tetrispvp.board;

import java.util.List;


public interface LineClearedListener {

    interface Line {
        int getLineNumber();
        List<GridField> getFieldsInLine();
    }

    void lineCleared(List<Line> clearedLines);
}
