package controller;

import controller.mocks.*;


public class GameController implements BlockSpawnedListener {

    private Mode gameMode;
    private final Network network;
    private final GUIController visualisation;
    private final TetrisBoard tetrisBoard;
    private final PowerUpBlockProvider blockProvider;

    private HumanController humanController;
    private AIController AIController;


    public GameController(GUIController guiController) {
        this.network = new Network();
        this.tetrisBoard = new TetrisBoard();
        this.blockProvider = PowerUpBlockProvider.getPowerUpBlockProvider();
        this.visualisation = guiController;
    }

    private void initGame() {
        this.tetrisBoard.addBlockSpawnedListener(this);
        this.visualisation.setKeyPressedHandler(humanController.getEventHandler());

        switch (gameMode) {
            case AI:

                break;
            case PVP:
                this.humanController = new HumanController(new MoveController(tetrisBoard, new TetrisTimer(1000)));
                break;
        }

    }

    /* integration with board */
    @Override
    public void blockSpawned() {
        Block block = blockProvider.getBlockWithPowerUp();
        tetrisBoard.spawnNewBlock(block);
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

    public void startGame() {
        initGame();
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
        this.AIController = new AIController(new MoveController(tetrisBoard, new TetrisTimer(1000)), tetrisBoard, difficultyLevel);
    }

}
