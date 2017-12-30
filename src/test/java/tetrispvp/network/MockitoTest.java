package tetrispvp.network;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MockitoTest {

    interface Database {
        int handleQuery(String q);
    }

    class ClientToTest {
        private final Database d;

        ClientToTest(Database d) {
            this.d = d;
        }

        int sendQuery(String q) {
            return d.handleQuery(q);
        }
    }

    @Test
    public void testQuery() {
        Database databaseMock = Mockito.mock(Database.class);
        when(databaseMock.handleQuery("* from t")).thenReturn(6);

        ClientToTest t = new ClientToTest(databaseMock);
        int check = t.sendQuery("* from t");
        assertTrue(check == 6);

        verify(databaseMock).handleQuery("* from t");
    }
}