package powerUps;

public class StraightLineNextPowerUp implements PowerUp{

	private int maxAllowedSimultaneousActivations = 3;
	private static StraightLineNextPowerUp straightLineNextPowerUp = null;

	private StraightLineNextPowerUp(){}

	public static StraightLineNextPowerUp getStraightLineNextPowerUp(){
		if(straightLineNextPowerUp == null){
			straightLineNextPowerUp = new StraightLineNextPowerUp();
		}
		return straightLineNextPowerUp;
	}

	@Override
	public void activate(int powerUpPresence) {
		if(powerUpPresence > maxAllowedSimultaneousActivations)
			powerUpPresence = maxAllowedSimultaneousActivations;

		//BlockGenerator.getBlockGenerator.nextStraightLineBlock(powerUpPresence);
	}
}
