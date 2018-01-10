package tetrispvp.board;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import tetrispvp.board.Mocks.MessageReceiver;
import tetrispvp.board.Mocks.MessageSender;
import tetrispvp.board.Mocks.NetworkModuleMock;

public class TetrisBoardModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(TetrisBoard.class).in(Singleton.class);
        bind(DTETBlockMover.class).in(Singleton.class);
        bind(BlockMover.class).to(DTETBlockMover.class);
        bind(GameStateTracker.class).to(GameStateNetworkSynchronizer.class);
        bind(MutableBoard.class).to(TetrisBoard.class);
        bind(Board.class).to(TetrisBoard.class);
        bind(CollisionChecker.class).to(NormalCollisionChecker.class);
        bind(GreyLinesManager.class).in(Singleton.class);
        bind(MessageReceiver.class).to(NetworkModuleMock.class);
        bind(MessageSender.class).to(NetworkModuleMock.class);
    }
}
