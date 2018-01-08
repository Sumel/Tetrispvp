package tetrispvp.board;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class TetrisBoardModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(BlockMover.class).to(DTETBlockMover.class).in(Singleton.class);
        bind(MutableBoard.class).to(TetrisBoard.class).in(Singleton.class);
    }
}
