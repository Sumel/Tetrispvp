package tetrispvp.board;


import com.google.inject.Inject;
import tetrispvp.board.Mocks.Block;
import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageHandler;
import tetrispvp.network.MessageReceiver;
import tetrispvp.network.MessageSender;

import java.util.LinkedList;
import java.util.List;

public class GameStateNetworkSynchronizer implements GameStateTracker {
    private List<GameStateChangedListener> listeners = new LinkedList<GameStateChangedListener>();
    private GameState currentState = GameState.InProgress;
    private BlockMover blockMover;
    private MessageSender sender;
    private MessageReceiver receiver;

    @Inject
    public GameStateNetworkSynchronizer(BlockMover blockMover, MessageSender sender, MessageReceiver receiver) {
        this.blockMover = blockMover;
        this.sender = sender;
        this.receiver = receiver;
        blockMover.addBlockSpawnedListener(new BlockSpawnedListener() {
            @Override
            public void blockSpawned(Block block) {
                if (blockMover.isBlockCollidingBelow()) {
                    if (getCurrentGameState() == GameState.InProgress) {
                        initiateStateChange(GameState.Lost);
                    } else if (getCurrentGameState() == GameState.Won) {
                        initiateStateChange(GameState.Draw);
                    }
                }
            }
        });

        receiver.expect("stateChangedOnOtherSide", new MessageHandler() {
            @Override
            public void arrived(String messageName, Object with, MessageContext within) {
                GameState otherSideState = (GameState) with;
                if (otherSideState == getCurrentGameState() && otherSideState != GameState.InProgress) {
                    initiateStateChange(GameState.Draw);
                    return;
                }
                if (otherSideState == GameState.Won && getCurrentGameState() == GameState.InProgress) {
                    setCurrentState(GameState.Lost);
                    return;
                }
                if (otherSideState == GameState.Lost && getCurrentGameState() == GameState.InProgress) {
                    setCurrentState(GameState.Won);
                    return;
                }
                if (otherSideState == GameState.Draw) {
                    setCurrentState(GameState.Draw);
                }
            }

            @Override
            public boolean shouldBeForgotten() {
                return false;
            }

            @Override
            public void wasForgotten() {

            }
        });

    }

    @Override
    public void addGameStateChangedListener(GameStateChangedListener newListener) {
        listeners.add(newListener);
    }

    @Override
    public GameState getCurrentGameState() {
        return currentState;
    }

    private void initiateStateChange(GameState newState) {
        setCurrentState(newState);
        sender.send("stateChangedOnOtherSide", newState);
    }

    private void setCurrentState(GameState newCurrentState) {
        if (currentState != newCurrentState) {
            GameState oldState = currentState;
            currentState = newCurrentState;
            for (GameStateChangedListener listener : listeners) {
                listener.stateChanged(oldState, newCurrentState);
            }
        }
    }

}
