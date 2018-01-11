package aiModule;

import aiModule.mocks.AIBoard;
import aiModule.mocks.GridField;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScoreTest {

    static BoardStatus getEmptyBoard() {
        ArrayList<List<GridField>> status = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            List<GridField> gridField = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                gridField.add(new GridField());
            }
            status.add(0, gridField);
        }
        return new BoardStatus(status);
    }


    @Test
    public void countGridFieldsTest() {
        BoardStatus bs = getEmptyBoard();
        for (int i = 0; i < 10; i++) {
            // bottom line empty, second line full
            bs.insertAt(i, 20, new GridField(true));
        }
        assertEquals(10, Score.countGridFields(bs));

        bs.insertAt(7, 13, new GridField(true));
        assertEquals(11, Score.countGridFields(bs));
        bs.insertAt(7, 13, new GridField(false));
        assertEquals(10, Score.countGridFields(bs));

    }

    @Test
    public void getDeletedLinesTest() {
        BoardStatus bs = getEmptyBoard();
        for (int i = 0; i < 10; i++) {
            // bottom line half empty, second line full
            bs.insertAt(i, 20, new GridField(true));
            if (i < 5) {
                bs.insertAt(i, 21, new GridField(true));
            }
        }

        Score score = new Score(bs, Score.defaultCoefficients);
        AIBoard aiBoard = new AIBoard(bs.value);
        aiBoard.removeLines();
        bs = new BoardStatus(aiBoard.getBoardState());

        assertEquals(1, score.getDeletedLines(bs));

        for (int i = 5; i < 10; i++) {
            bs.insertAt(i, 21, new GridField(true));
        }

        aiBoard = new AIBoard(bs.value);
        aiBoard.removeLines();
        bs = new BoardStatus(aiBoard.getBoardState());

        assertEquals(1, score.getDeletedLines(bs));

    }

    @Test
    public void gestValueTest() {
        Score score = new Score(getEmptyBoard(), Score.defaultCoefficients);

        score.deletedLines = 10;
        score.holes = 3;
        score.bumpiness = 12;
        score.height = 30;

        double expectedValue = 10 * 0.5 + 3 * -0.5 + 12 * -0.5 + 30 * -0.5;
        assertEquals(score.getValue(), expectedValue, 1e-9);

    }

}
