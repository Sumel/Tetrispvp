package powerUps;

public class ReverseBoard implements PowerUp {

	private static ReverseBoard reverseBoard = null;

	private ReverseBoard() {}

	public static ReverseBoard getReverseBoard(){
        if(reverseBoard == null)
            reverseBoard = new ReverseBoard();
        return reverseBoard;
    }


	@Override
	public void activate(int powerUpPresence) {
		if (powerUpPresence % 2 != 0)
			PowerUpManager.getPowerUpManager().getMockNetwork().send("flipBoard", null);
	}
}
