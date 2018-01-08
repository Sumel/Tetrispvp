package tetrispvp.network;

import org.junit.Test;
import tetrispvp.network.detail.impl.HandlerStore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertEquals;

public class HandlerStoreTest {

    class Handler implements MessageHandler {

        private Map<String, Integer> counter = new HashMap<>();

        @Override
        public synchronized void arrived(String messageName, Object with, MessageContext within) {
            if (counter.containsKey(messageName))
                counter.put(messageName, counter.get(messageName) + 1);
            else
                counter.put(messageName, 1);
        }

        @Override
        public boolean shouldBeForgotten() {
            return false;
        }

        @Override
        public void wasForgotten() {

        }

        public int entryCount() {
            return counter.size();
        }

        public synchronized int countFor(String messageName) {
            return counter.getOrDefault(messageName, 0);
        }
    }

    @Test
    public void testHandler() {

        int threadPoolSize = 8;
        HandlerStore store = new HandlerStore(null, threadPoolSize);

        Handler handler1 = new Handler();
        Handler handler2 = new Handler();

        for (int i = 0; i < 16; i++) {
            store.expect("message" + i, handler1);
            if (i % 2 == 0)
                store.expect("message" + i, handler2);
        }

        store.expect(Pattern.compile("a{1,}p"), handler1);
        store.expect("aaap", handler2);

        for (int i = 0; i < 16; i += 2)
            store.received("message" + i, null);
        for (int i = 0; i < 16; i++)
            store.received("message" + i, null);

        for (int i = 0; i < 16; i++) {
            String a = "";
            for (int j = 0; j < i; j++)
                a += "a";
            String message = a + "p";
            store.received(message, null);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(16 + 15, handler1.entryCount());
        assertEquals(8 + 1, handler2.entryCount());

        for (int i = 0; i < 16; i++) {
            String message = "message" + i;
            if (i % 2 == 0) {
                assertEquals(2, handler1.countFor(message));
                assertEquals(2, handler2.countFor(message));
            } else {
                assertEquals(1, handler1.countFor(message));
                assertEquals(0, handler2.countFor(message));
            }
        }

        for (int i = 0; i < 16; i++) {
            String a = "";
            for (int j = 0; j < i; j++)
                a += "a";
            String message = a + "p";

            if (i >= 1)
                assertEquals(1, handler1.countFor(message));
            else
                assertEquals(0, handler1.countFor(message));

            if (message.equals("aaap"))
                assertEquals(1, handler2.countFor(message));
            else
                assertEquals(0, handler2.countFor(message));

        }
    }
}