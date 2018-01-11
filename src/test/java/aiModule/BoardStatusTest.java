package aiModule;

import aiModule.mocks.GridField;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BoardStatusTest {

    @Test
    public void getHolesTest() {
        BoardStatus bs = ScoreTest.getEmptyBoard();

        for (int i = 0; i < 10; i++) {
            bs.insertAt(i, i % 2 == 0 ? 21 : 20, new GridField(true));
        }

        assertEquals(5, bs.getHoles() );
    }

    @Test
    public void getBumpinessTest() {
        BoardStatus bs = ScoreTest.getEmptyBoard();
        for (int i = 0; i < 10; i++) {
            if(i!=7){
                bs.insertAt(i, 0, new GridField(true));
            }
        }
        assertEquals(44, bs.getBumpiness());

        bs = ScoreTest.getEmptyBoard();
        for (int i = 0; i < 10; i++) {
                bs.insertAt(i, 10, new GridField(true));
        }
        assertEquals(0, bs.getBumpiness());

        bs.insertAt(0, 8, new GridField(true));
        assertEquals(2, bs.getBumpiness());

        bs.insertAt(0, 5, new GridField(true));
        assertEquals(5, bs.getBumpiness());

        bs.insertAt(0, 15, new GridField(true));
        assertEquals(5, bs.getBumpiness());

    }

    @Test
    public void getColumnHeight(){
        BoardStatus bs = ScoreTest.getEmptyBoard();

        for (int i = 0; i < 10; i++) {
            assertEquals(0, bs.getColumnHeight(i));
        }

        bs.insertAt(0, 5, new GridField(true));
        assertEquals(22-5, bs.getColumnHeight(0));

        bs.insertAt(0, 5, new GridField(false));
        assertEquals(0, bs.getColumnHeight(0));

    }

    @Test
    public void getAggregateHeight(){
        BoardStatus bs = ScoreTest.getEmptyBoard();
        assertEquals(0, bs.getAggregateHeight());

        bs.insertAt(4, 0, new GridField(true));
        assertEquals(22, bs.getAggregateHeight());

        bs.insertAt(7, 20, new GridField(true));
        assertEquals(24, bs.getAggregateHeight());

        bs.insertAt(7, 21, new GridField(true));
        assertEquals(24, bs.getAggregateHeight());
    }

}
