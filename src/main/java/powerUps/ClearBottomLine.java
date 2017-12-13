package powerUps;

public interface ClearBottomLine extends PowerUp {
	
	int maxAllowedSimultaneousActivations = 2;
	
	void clearLine();

}
