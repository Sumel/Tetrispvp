package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.mocks.GridField;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class FirstMockTest {
    @Test
    public void NoPowerUpInField(){
        GridField f = mock(GridField.class);
        assertEquals(false, f.hasPowerUp());
    }
}
