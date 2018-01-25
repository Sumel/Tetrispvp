package powerUps;

public class ClearBottomLine implements PowerUp {
	
	private int maxAllowedSimultaneousActivations = 2;
	private static ClearBottomLine clearBottomLine = null;

	private ClearBottomLine() { }
	
	public static ClearBottomLine getClearBottomLine(){
		if(clearBottomLine == null)
			clearBottomLine = new ClearBottomLine();
		return clearBottomLine;
	}

	private void clearLine(){
		PowerUpManager.getPowerUpManager().getBoard().
			clearLine(PowerUpManager.getPowerUpManager().getBoard().getHeight() - 1);
	}

	@Override
	public void activate(int powerUpPresence) {
		if(powerUpPresence > maxAllowedSimultaneousActivations)
			powerUpPresence = maxAllowedSimultaneousActivations;
		for(int i = 0; i < powerUpPresence; i++)
			clearLine();
	}
}
