package tetrispvp.board;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class TetrisBoardModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(TetrisBoard.class).in(Singleton.class);
        bind(BlockMover.class).to(DTETBlockMover.class);
        bind(MutableBoard.class).to(TetrisBoard.class);
        bind(Board.class).to(TetrisBoard.class);
        bind(CollisionChecker.class).to(NormalCollisionChecker.class);
    }
}
