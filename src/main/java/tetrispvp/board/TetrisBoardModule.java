package tetrispvp.board;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import tetrispvp.board.NetworkIntegration.LastMessageSender;
import tetrispvp.network.MessageReceiver;
import tetrispvp.network.MessageSender;
import tetrispvp.board.NetworkIntegration.LastMessageReceiver;

public class TetrisBoardModule extends AbstractModule {
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
        bind(MessageReceiver.class).to(LastMessageReceiver.class);
        bind(MessageSender.class).to(LastMessageSender.class);
    }
}
