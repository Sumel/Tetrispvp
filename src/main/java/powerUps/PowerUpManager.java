package powerUps;

public interface PowerUpManager {
	
	PowerUp randomPowerUp(); 
	void checkForPowerUps(List<BlockField> lines);

}
