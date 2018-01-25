package powerUps;

import java.util.List;

import powerUps.mocks.GridField;
import powerUps.mocks.LinesClearedListener;

public class PowerUpLinesClearedListener implements LinesClearedListener {

	@Override
	public void lineCleared(List<GridField> lines) {
		powerUps.PowerUpManager.getPowerUpManager().lineCleared(lines);
	}

}
