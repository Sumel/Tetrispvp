package powerUps;

public class AddMoreLinesPowerUp implements PowerUp{
	
	int maxAllowedSimultaneousActivations = 2;
	private static AddMoreLinesPowerUp addMoreLinesPowerUp = null;

	private AddMoreLinesPowerUp() {}

    public static AddMoreLinesPowerUp getAddMoreLinesPowerUp(){
        if(addMoreLinesPowerUp == null)
            addMoreLinesPowerUp = new AddMoreLinesPowerUp();
        return addMoreLinesPowerUp;
    }

	@Override
	public void activate(int powerUpPresence) {
		if(powerUpPresence > maxAllowedSimultaneousActivations)
			powerUpPresence = maxAllowedSimultaneousActivations;
        PowerUpManager.getPowerUpManager().getMockNetwork().send("addLines", powerUpPresence);
	}
}
