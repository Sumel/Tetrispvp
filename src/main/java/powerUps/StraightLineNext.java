package powerUps;

public class StraightLineNext implements PowerUp{

	private int maxAllowedSimultaneousActivations = 2;
	private static StraightLineNext straightLineNext = null;

	private StraightLineNext(){}

	public static StraightLineNext getStraightLineNext(){
		if(straightLineNext == null){
			straightLineNext = new StraightLineNext();
		}
		return straightLineNext;
	}
	
	private void changeNextBlockToStraightLine(int howMany){
		PowerUpManager.getPowerUpManager().getGameController().
			setNextBlockAsStraightLine(howMany);
	}

	@Override
	public void activate(int powerUpPresence) {
		if(powerUpPresence > maxAllowedSimultaneousActivations)
			powerUpPresence = maxAllowedSimultaneousActivations;
		changeNextBlockToStraightLine(powerUpPresence);
	}
}
