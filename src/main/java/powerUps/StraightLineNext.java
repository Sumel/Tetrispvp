package powerUps;

public class StraightLineNext implements PowerUp{

	private int maxAllowedSimultaneousActivations = 3;
	private static StraightLineNext straightLineNext = null;

	private StraightLineNext(){}

	public static StraightLineNext getStraightLineNext(){
		if(straightLineNext == null){
			straightLineNext = new StraightLineNext();
		}
		return straightLineNext;
	}

	@Override
	public void activate(int powerUpPresence) {
		if(powerUpPresence > maxAllowedSimultaneousActivations)
			powerUpPresence = maxAllowedSimultaneousActivations;

		PowerUpManager.getPowerUpManager().getGameController().
				setNextBlockAsStraightLine(powerUpPresence);
	}
}
