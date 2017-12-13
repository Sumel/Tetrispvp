package powerUps;

public interface StraightLineNext extends PowerUp{

	int maxAllowedSimultaneousActivations = 2;
	
	void changeNextBlockToStraightLine();
	
}
