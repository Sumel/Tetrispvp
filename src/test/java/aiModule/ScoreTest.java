package aiModule;

import aiModule.mocks.GridField;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScoreTest {
    private Coefficients defaultCoefficients =
            new Coefficients(-.5, 0.5, -0.5, -0.5);


    private BoardStatus getEmptyBoard() {
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
        assertEquals(Score.countGridFields(bs), 10);

        bs.insertAt(7, 13, new GridField(true));
        assertEquals(Score.countGridFields(bs), 11);
        bs.insertAt(7, 13, new GridField(false));
        assertEquals(Score.countGridFields(bs), 10);

    }

}
