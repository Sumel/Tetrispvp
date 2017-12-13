package powerUps;

public interface AddMoreLines extends PowerUp{
	
	int maxAllowedSimultaneousActivations = 1;
	int numberOfAddingLines = 1;
	
	void informAboutMoreLines();

}
