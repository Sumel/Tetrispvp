package powerUps;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import powerUps.mocks.*;

import javax.sound.sampled.Line;

public class PowerUpManager {
    private static PowerUpManager powerUpManager = null;
    private Random random = new Random();
    private MutableBoard board;
    private GameController gameController;
    private MockNetwork mockNetwork;
    private LinesClearedListener linesClearedListener = new PowerUpLinesClearedListener();

	private PowerUpManager() { }

    public static PowerUpManager getPowerUpManager(){
        if(powerUpManager == null){
            powerUpManager = new PowerUpManager();
        }
        return powerUpManager;
    }

    public int randomPowerUp(){
        int powerUp = random.nextInt(PowerUpTypes.values().length);
        System.out.println(PowerUpTypes.values()[powerUp]);
        return powerUp;
	}

	public int[] checkForPowerUps(List<GridField> lines){
        int[] powerUpsPresence = new int[PowerUpTypes.values().length];
        for(GridField field: lines){
            if(field.hasPowerUp()){
                powerUpsPresence[(int)field.getPowerUp()]++;
            }
        }

        return powerUpsPresence;
    }

    public void lineCleared(List<GridField> lines) {
        int[] powerUpsPresence = checkForPowerUps(lines);
        for(int i = 0; i < powerUpsPresence.length; i++){
        	if(powerUpsPresence[i] > 0){
                switch (PowerUpTypes.values()[i]){
                case ADD_MORE_LINES:
                    AddMoreLines.getAddMoreLines().activate(powerUpsPresence[i]);
                    break;
                case CLEAR_BOTTOM_LINE:
                    ClearBottomLine.getClearBottomLine().activate(powerUpsPresence[i]);
                    break;
                case REVERSE_BOARD:
                    ReverseBoard.getReverseBoard().activate(powerUpsPresence[i]);
                    break;
                case STRAIGHT_LINE_NEXT:
                    StraightLineNext.getStraightLineNext().activate(powerUpsPresence[i]);
                    break;
                default:
                	break;
                }
        	}

        }
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
        board.addLinesClearedListener(this.linesClearedListener);
    }

    public MockNetwork getMockNetwork() {
        return mockNetwork;
    }

    public void setMockNetwork(MockNetwork mockNetwork) {
        this.mockNetwork = mockNetwork;
    }
}
