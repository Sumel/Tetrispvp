package tetrispvp.powerUp;

import org.junit.Test;
import powerUps.PowerUpManager;
import powerUps.StraightLineNext;
import powerUps.mocks.Block;
import powerUps.mocks.BlockType;
import powerUps.mocks.GameController;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StraightLineNextTest {
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
        StraightLineNext straightLineNext1 = StraightLineNext.getStraightLineNext();
        StraightLineNext straightLineNext2 = StraightLineNext.getStraightLineNext();

        assertEquals(straightLineNext1, straightLineNext2);
    }

    @Test
    public void addOneStraightLine(){
        StraightLineNext straightLineNext = StraightLineNext.getStraightLineNext();
        GameController gameController = getGameController();
        PowerUpManager.getPowerUpManager().setGameController(gameController);

        straightLineNext.activate(1);
        List<Block> nextBlocks = gameController.getNextBlocks();

        assertTrue(nextBlocks.get(0).getType() == BlockType.STRAIGHT_LINE);
    }

    @Test
    public void addTwoStraightLines(){
        StraightLineNext straightLineNext = StraightLineNext.getStraightLineNext();
        GameController gameController = getGameController();
        PowerUpManager.getPowerUpManager().setGameController(gameController);

        straightLineNext.activate(2);
        List<Block> nextBlocks = gameController.getNextBlocks();

        assertTrue(nextBlocks.get(0).getType() == BlockType.STRAIGHT_LINE &&
                nextBlocks.get(1).getType() == BlockType.STRAIGHT_LINE);
    }

    @Test
    public void addThreeStraightLines(){
        StraightLineNext straightLineNext = StraightLineNext.getStraightLineNext();
        GameController gameController = getGameController();
        PowerUpManager.getPowerUpManager().setGameController(gameController);

        straightLineNext.activate(3);
        List<Block> nextBlocks = gameController.getNextBlocks();

        for(int i = 0; i < 3; i++){
            assertTrue(nextBlocks.get(i).getType() == BlockType.STRAIGHT_LINE);
        }
    }

    @Test
    public void addMoreThanGameControllerSizeStraightLines(){
        StraightLineNext straightLineNext = StraightLineNext.getStraightLineNext();
        GameController gameController = getGameController();
        PowerUpManager.getPowerUpManager().setGameController(gameController);

        straightLineNext.activate(gameController.getNextBlocks().size()+1);
        List<Block> nextBlocks = gameController.getNextBlocks();

        for(Block b:nextBlocks){
            assertTrue(b.getType() == BlockType.STRAIGHT_LINE);
        }
    }
}
