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
	
	private void changeNextBlockToStraightLine(){

	}

	@Override
	public void activate(int powerUpPresence) {

	}
}
