package controller;

import controller.mocks.*;

import java.util.Optional;

public class GameController implements BlockSpawnedListener {

    private Mode gameMode;
    private final Network network;
    private final GUIController visualisation;
    private final TetrisBoard humanTetrisBoard;
    private final HumanController humanController;
    private final PowerUpBlockProvider blockProvider;

    private Optional<TetrisBoard> AITetrisBoard;
    private Optional<AIController> AIController;


    public GameController() {
        this.network = new Network();
        this.humanTetrisBoard = new TetrisBoard();
        this.blockProvider = PowerUpBlockProvider.getPowerUpBlockProvider();
         this.humanController = new HumanController(new MoveController(humanTetrisBoard, new TetrisTimer(1000)));
        this.visualisation = new GUIController();
    }

    public void initGame() {
        this.humanTetrisBoard.addBlockSpawnedListener(this);
        this.visualisation.setKeyPressedHandler(humanController.getEventHandler());

        switch (gameMode) {
            case AI:
                break;
            case PVP:
                break;
        }

    }

    /* integration with board */
    @Override
    public void blockSpawned() {
        Block block = blockProvider.getBlockWithPowerUp();
        humanTetrisBoard.spawnNewBlock(block);
        visualisation.updateNextBlockView(block);
    }


    /* integration with GUIController */

    public void setGameMode(Mode mode) {
        this.gameMode = mode;
    }

    public void setIP(String IP) {
        establishNetworkConnection(IP);
    }

    public void setAIDifficulty(double difficultyLevel) {
        initialiseAI(difficultyLevel);
    }

    public void closeGame() {
        breakNetworkConnection();
    }

    /* integration with network */

    private void establishNetworkConnection(String IP) {
        network.connect(IP);
    }

    private void breakNetworkConnection() {
        network.disconnect();
    }

    /* integration with AI */

    private void initialiseAI(double difficultyLevel) {
        this.AITetrisBoard = Optional.of(new TetrisBoard());
        this.AIController = Optional.of(new AIController(new MoveController(AITetrisBoard.get(), new TetrisTimer(1000)), AITetrisBoard.get(), difficultyLevel));
    }

}
