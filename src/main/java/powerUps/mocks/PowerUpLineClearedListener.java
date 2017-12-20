package powerUps.mocks;

import java.util.List;

public class PowerUpLineClearedListener implements LineClearedListener {

	@Override
	public void lineCleared(List<GridField> lines) {
		powerUps.PowerUpManager.checkForPowerUps(lines));
	}

}
