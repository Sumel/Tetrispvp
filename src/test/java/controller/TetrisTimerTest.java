package controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TetrisTimerTest {

    @Test
    public void testResetAfter100ms() throws InterruptedException {
        // given
        TetrisTimer timer = new TetrisTimer(30);
        // when
        timer.init();
        Thread.sleep(100);
        timer.reset();
        // then
        assertEquals(0, timer.counter);
    }

    @Test
    public void testAfter2sec() throws InterruptedException {
        //given
        TetrisTimer timer = new TetrisTimer(1000);
        //when
        timer.init();
        Thread.sleep(2000);
        // then
        assertEquals(2, timer.counter);
    }
}
