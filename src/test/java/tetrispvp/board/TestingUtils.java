package tetrispvp.board;

import java.util.ArrayList;
import java.util.List;

public class TestingUtils {
    public static List<List<GridField>> getEmptyBoard() {
        int width = 10;
        int height = 20;

        GridField emptyField = new BoardField();

        List<List<GridField>> board = new ArrayList<List<GridField>>();

        for (int row = 0; row < height; ++row) {
            List<GridField> newRow = new ArrayList<GridField>();
            for (int col = 0; col < width; ++col) {
                newRow.add(col, emptyField);
            }
            board.add(row, newRow);
        }

        return board;
    }
}
