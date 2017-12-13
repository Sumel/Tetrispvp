package powerUps;

public interface PowerUpManager {
	
	final double probabilityOfGettingPowerUp = 0.05;
	
	PowerUp randomPowerUp(); 
	void checkForPowerUps(int linesNumber, int topPosition);

}
