package powerUps;

public class ReverseBoardPowerUp implements PowerUp {

	private static ReverseBoardPowerUp reverseBoardPowerUp = null;

	private ReverseBoardPowerUp() {}

	public static ReverseBoardPowerUp getReverseBoardPowerUp(){
        if(reverseBoardPowerUp == null)
            reverseBoardPowerUp = new ReverseBoardPowerUp();
        return reverseBoardPowerUp;
    }


	@Override
	public void activate(int powerUpPresence) {
		if (powerUpPresence % 2 != 0)
			PowerUpManager.getPowerUpManager().getMockNetwork().send("flipBoard", null);
	}
}
