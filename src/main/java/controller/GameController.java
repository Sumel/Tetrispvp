package controller;

import mocks.*;

import java.util.Optional;

import static controller.GameMode.AI;
import static controller.GameMode.HUMAN;

public class GameController {

    int queueSize = 5;

    GameMode gameMode;

    PowerUpGenerator powerUpGenerator;
    BlockGenerator blockGenerator;

    Visualisation visualisation;

    HumanController humanController;
    Board humanBoard;

    Optional<Board> aiBoard;
    Optional<AIController> aiController;

    /**
     * User creates new game
     *      - option1: waits for a second player and starts game
     *      - option2: plays with AI
     */

    GameController(GameMode gameMode){
        this.gameMode = gameMode;
        this.powerUpGenerator = PowerUpGenerator.getPowerUpGenerator();
        this.blockGenerator = BlockGenerator.getBlockGenerator(queueSize);
        this.humanBoard = new Board();
        this.visualisation = new Visualisation();
        this.humanController = new HumanController(new MoveController(humanBoard, new TetrisTimer(1000)), visualisation);
        this.visualisation.addKeyListner(this.humanController);
    }

    public void initGame() {


        switch (gameMode) {
            case AI: {
                System.out.println("Initialising AI");
                this.aiBoard = Optional.of(new Board());
                this.aiController = Optional.of(new AIController(new MoveController(aiBoard.get(), new TetrisTimer(1000))));
                break;
            }
            case HUMAN: {
                System.out.println("Waiting for other player.");
                // wait for other player

                while (true) {
                    // spawn when the block lands on the board
                    // spawnBlock(humanBoard);
                }
            }
        }

    }

    /**
     * Spawn next Block with or without PowerUp - PowerUpManager decidec if powerUp is active or no
     */

//    private void spawnBlock(Board board) {
//        Block block = blockGenerator.nextRandomBlock();
//        PowerUp powerUp = powerUpGenerator.nextRandomPowerUp();
//        int powerUpPostition = powerUpGenerator.randPowerUpPosition();
//        block.setPowerUpAtFiled(powerUpPostition, powerUp);
//        board.spawnNextBlock(block);
//        visualisation.updateNextBlockView(block);
//    }

    private void updateView() {
        visualisation.updateView();
    }

    public void setAIDifficulty(double difficulty) {

    }
}
