package powerUps;

public interface StraightLineNext implements PowerUp{

	int maxAllowedSimultaneousActivations = 2;
	
	void changeNextBlockToStraightLine();
	
}
