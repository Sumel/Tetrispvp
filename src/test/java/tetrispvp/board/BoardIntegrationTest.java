package tetrispvp.board;


import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tetrispvp.board.Mocks.BlockImplementation;
import tetrispvp.network.NetworkModuleFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class BoardIntegrationTest {
    @Before
    public void startNetwork() {
        if (NetworkModuleFactory.last() == null) {
            NetworkModuleFactory.getNetworkModule();
        }
    }

    @After
    public void stopNetwork() {
        NetworkModuleFactory.last().connectionContext().disconnect();
    }

    @Test
    public void fromEmptyBoardToLineClear() {
        Injector injector = Guice.createInjector(new TetrisBoardModule());
        TetrisBoard board = injector.getInstance(TetrisBoard.class);
        LinesClearedListener listener = mock(LinesClearedListener.class);
        board.addLinesClearedListener(listener);


        //sample game
        board.spawnNewBlock(new BlockImplementation('I'));
        board.rotateCounterClockwise();
        board.moveToBottom();
        board.spawnNewBlock(new BlockImplementation('I'));
        board.rotateClockwise();
        board.moveLeft();
        board.moveLeft();
        board.moveLeft();
        board.moveLeft();
        for (int i = 0; i < 50; ++i) {
            board.moveDown();
        }
        board.spawnNewBlock(new BlockImplementation('I'));
        board.moveRight();
        board.moveRight();
        board.moveRight();
        board.moveToBottom();
        board.spawnNewBlock(new BlockImplementation('I'));
        board.moveLeft();
        board.moveLeft();
        board.moveLeft();
        board.moveLeft();
        board.moveLeft();
        board.moveLeft();
        board.moveLeft();
        board.moveLeft();
        board.moveLeft();
        board.moveToBottom();
        verify(listener, times(0)).linesCleared(any(List.class));
        board.spawnNewBlock(new BlockImplementation('I'));

        verify(listener, times(1)).linesCleared(any(List.class));


    }

    @Test
    public void fromEmptyBoardToLose() {
        Injector injector = Guice.createInjector(new TetrisBoardModule());
        TetrisBoard board = injector.getInstance(TetrisBoard.class);


        //sample game
        board.spawnNewBlock(new BlockImplementation('I'));
        board.moveToBottom();
        board.spawnNewBlock(new BlockImplementation('I'));
        board.moveToBottom();
        board.spawnNewBlock(new BlockImplementation('I'));
        board.moveToBottom();
        board.spawnNewBlock(new BlockImplementation('I'));
        board.moveToBottom();
        board.spawnNewBlock(new BlockImplementation('I'));
        board.moveToBottom();
        board.spawnNewBlock(new BlockImplementation('I'));
        board.moveToBottom();
        board.spawnNewBlock(new BlockImplementation('I'));
        board.moveToBottom();
        assertEquals(board.getCurrentGameState(), GameState.Lost);


    }
}
