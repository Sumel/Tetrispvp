package powerUps;

public interface AddMoreLines implements PowerUp{
	
	int maxAllowedSimultaneousActivations = 1;
	int numberOfAddingLines = 1;
	
	void informAboutMoreLines();

}
