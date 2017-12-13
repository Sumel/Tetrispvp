package powerUps;

public interface ClearBottomLine implements PowerUp {
	
	int maxAllowedSimultaneousActivations = 2;
	
	void clearLine();

}
