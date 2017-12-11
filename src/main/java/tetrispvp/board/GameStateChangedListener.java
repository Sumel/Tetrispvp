package tetrispvp.board;

public interface GameStateChangedListener {
    void stateChanged(GameState oldState, GameState newState);
}
