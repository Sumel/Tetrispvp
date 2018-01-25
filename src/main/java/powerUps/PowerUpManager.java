package powerUps;

import java.util.List;
import java.util.Random;

import powerUps.NetworkIntegration.RealNetwork;
import tetrispvp.board.*;
import powerUps.mocks.MockNetwork;
import powerUps.mocks.MockHandler;
import static powerUps.AddMoreLinesPowerUp.getAddMoreLinesPowerUp;
import static powerUps.ClearBottomLinePowerUp.getClearBottomLinePowerUp;
import static powerUps.ReverseBoardPowerUp.getReverseBoardPowerUp;
import static powerUps.StraightLineNextPowerUp.getStraightLineNextPowerUp;

public class PowerUpManager {
    private static PowerUpManager powerUpManager = null;
    private Random random = new Random();
    private MutableBoard board;
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
                powerUpsPresence[(int)field.getPowerUpID()]++;
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
                    getAddMoreLinesPowerUp().activate(powerUpsPresence[i]);
                    break;
                case CLEAR_BOTTOM_LINE:
                    getClearBottomLinePowerUp().activate(powerUpsPresence[i]);
                    break;
                case REVERSE_BOARD:
                    getReverseBoardPowerUp().activate(powerUpsPresence[i]);
                    break;
                case STRAIGHT_LINE_NEXT:
                    getStraightLineNextPowerUp().activate(powerUpsPresence[i]);
                    break;
                default:
                	break;
                }
        	}

        }
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
        mockNetwork.expect("flipBoard", new MockHandler(getBoard()));
        mockNetwork.expect("addLines", new MockHandler(getBoard()));
    }

    public void initNetwork() {
        mockNetwork = new RealNetwork();
        setMockNetwork(mockNetwork);
    }

    public LinesClearedListener getLinesClearedListener(){
    	return this.linesClearedListener;
    }
}
