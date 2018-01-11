package powerUps;

public class ClearBottomLinePowerUp implements PowerUp {
	
	private int maxAllowedSimultaneousActivations = 2;
	private static ClearBottomLinePowerUp clearBottomLinePowerUp = null;

	private ClearBottomLinePowerUp() { }
	
	public static ClearBottomLinePowerUp getClearBottomLinePowerUp(){
		if(clearBottomLinePowerUp == null)
			clearBottomLinePowerUp = new ClearBottomLinePowerUp();
		return clearBottomLinePowerUp;
	}

	private void clearLine(){
		PowerUpManager.getPowerUpManager().getBoard().
			clearLine(PowerUpManager.getPowerUpManager().getBoard().highestRowWithLockedFields() - 1);
	}

	@Override
	public void activate(int powerUpPresence) {
		if(powerUpPresence > maxAllowedSimultaneousActivations)
			powerUpPresence = maxAllowedSimultaneousActivations;
		for(int i = 0; i < powerUpPresence; i++)
			clearLine();
	}
}
