package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.PowerUpManager;
import powerUps.StraightLineNextPowerUp;
import powerUps.mocks.Block;
import powerUps.mocks.BlockType;
import powerUps.mocks.GameController;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StraightLineNextPowerUpTest {
    private GameController getGameController(){
        GameController gameController = new GameController();

        gameController.addRandomBlockNext();
        gameController.addRandomBlockNext();
        gameController.addRandomBlockNext();
        gameController.addRandomBlockNext();

        return gameController;
    }

    @Test
    public void getSameStraightLineInstance(){
        StraightLineNextPowerUp straightLineNextPowerUp1 = StraightLineNextPowerUp.getStraightLineNextPowerUp();
        StraightLineNextPowerUp straightLineNextPowerUp2 = StraightLineNextPowerUp.getStraightLineNextPowerUp();

        assertEquals(straightLineNextPowerUp1, straightLineNextPowerUp2);
    }

    @Test
    public void addOneStraightLine(){
        StraightLineNextPowerUp straightLineNextPowerUp = StraightLineNextPowerUp.getStraightLineNextPowerUp();
        GameController gameController = getGameController();
        PowerUpManager.getPowerUpManager().setGameController(gameController);
        gameController.getNextBlocks();

        straightLineNextPowerUp.activate(1);
        List<Block> nextBlocks = gameController.getNextBlocks();

        assertTrue(nextBlocks.get(0).getType() == BlockType.STRAIGHT_LINE);
    }

    @Test
    public void addTwoStraightLines(){
        StraightLineNextPowerUp straightLineNextPowerUp = StraightLineNextPowerUp.getStraightLineNextPowerUp();
        GameController gameController = getGameController();
        PowerUpManager.getPowerUpManager().setGameController(gameController);
        gameController.getNextBlocks();

        straightLineNextPowerUp.activate(2);
        List<Block> nextBlocks = gameController.getNextBlocks();

        assertTrue(nextBlocks.get(0).getType() == BlockType.STRAIGHT_LINE &&
                nextBlocks.get(1).getType() == BlockType.STRAIGHT_LINE);
    }

    @Test
    public void addThreeStraightLines(){
        StraightLineNextPowerUp straightLineNextPowerUp = StraightLineNextPowerUp.getStraightLineNextPowerUp();
        GameController gameController = getGameController();
        PowerUpManager.getPowerUpManager().setGameController(gameController);
        gameController.getNextBlocks();

        straightLineNextPowerUp.activate(3);
        List<Block> nextBlocks = gameController.getNextBlocks();

        for(int i = 0; i < 3; i++){
            assertTrue(nextBlocks.get(i).getType() == BlockType.STRAIGHT_LINE);
        }
    }

    @Test
    public void addMoreThanGameControllerSizeStraightLines(){
        StraightLineNextPowerUp straightLineNextPowerUp = StraightLineNextPowerUp.getStraightLineNextPowerUp();
        GameController gameController = getGameController();
        PowerUpManager.getPowerUpManager().setGameController(gameController);

        straightLineNextPowerUp.activate(gameController.getNextBlocks().size()+1);
        List<Block> nextBlocks = gameController.getNextBlocks();

        for(Block b:nextBlocks){
            assertTrue(b.getType() == BlockType.STRAIGHT_LINE);
        }
    }
}
