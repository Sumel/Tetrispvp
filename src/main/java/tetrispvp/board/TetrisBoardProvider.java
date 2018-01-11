package tetrispvp.board;


import com.google.inject.Guice;
import com.google.inject.Injector;

public class TetrisBoardProvider {
    private static TetrisBoard board = null;

    public static TetrisBoard getTetrisBoard() {
        if (board != null) {
            return board;
        }
        Injector injector = Guice.createInjector(new TetrisBoardModule());
        board = injector.getInstance(TetrisBoard.class);
        injector.getInstance(GreyLinesManager.class);
        return board;
    }
}
