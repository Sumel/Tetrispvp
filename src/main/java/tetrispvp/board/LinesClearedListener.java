package tetrispvp.board;

import java.util.List;


public interface LinesClearedListener {

    interface Line {
        int getLineNumber();

        List<GridField> getFieldsInLine();
    }

    void linesCleared(List<Line> lines);
}
