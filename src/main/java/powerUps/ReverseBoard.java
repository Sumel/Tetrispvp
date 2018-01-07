package powerUps;

public class ReverseBoard implements PowerUp {
	
	private void informToReverseBoard(){

	}


	@Override
	public void activate(int powerUpPresence) {
		if (powerUpPresence % 2 != 0)
			informToReverseBoard();
	}
}
