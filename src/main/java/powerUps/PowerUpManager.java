package powerUps;

import java.util.List;
import java.util.Random;

import powerUps.mocks.*;

public class PowerUpManager {
    private static PowerUpManager powerUpManager = null;
    private Random random = new Random();
    private MutableBoard board;
    private GameController gameController;

	private PowerUpManager() {
		
    }

    public static PowerUpManager getPowerUpManager(){
        if(powerUpManager == null){
            powerUpManager = new PowerUpManager();
        }
        return powerUpManager;
    }
    
    public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public MutableBoard getBoard() {
		return board;
	}

	public void setBoard(MutableBoard board) {
		this.board = board;
	}

    public int randomPowerUp(){
        int powerUp = random.nextInt(PowerUpTypes.values().length);
        System.out.println(PowerUpTypes.values()[powerUp]);
        return powerUp;
	}

	public void checkForPowerUps(List<GridField> lines){
        int[] powerUpsPresence = new int[PowerUpTypes.values().length];
        for(GridField field: lines){
            if(field.hasPowerUp()){
                powerUpsPresence[(int)field.getPowerUp()]++;
            }
        }

        for(int i = 0; i < powerUpsPresence.length; i++){
            switch (PowerUpTypes.values()[i]){
                case ADD_MORE_LINES:
                    break;
                case CLEAR_BOTTOM_LINE:
                	ClearBottomLine.getClearBottomLine().activate(powerUpsPresence[i]);
                    break;
                case REVERSE_BOARD:
                    break;
                case STRAIGHT_LINE_NEXT:
                    StraightLineNext.getStraightLineNext().activate(powerUpsPresence[i]);
                    break;
            }
        }

    }

}
