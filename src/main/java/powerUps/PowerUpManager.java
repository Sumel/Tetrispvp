package powerUps;

import java.util.List;

import powerUps.mocks.GridField;

public interface PowerUpManager {
	
	PowerUp randomPowerUp(); 
	void checkForPowerUps(List<GridField> lines);

}
