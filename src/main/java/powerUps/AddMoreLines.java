package powerUps;

public class AddMoreLines implements PowerUp{
	
	int maxAllowedSimultaneousActivations = 1;
	int numberOfAddingLines = 1;
	private static AddMoreLines addMoreLines = null;

	private AddMoreLines() {}

    public static AddMoreLines getAddMoreLines(){
        if(addMoreLines == null)
            addMoreLines = new AddMoreLines();
        return addMoreLines;
    }

	@Override
	public void activate(int powerUpPresence) {
		if(powerUpPresence > maxAllowedSimultaneousActivations)
			powerUpPresence = maxAllowedSimultaneousActivations;


	}
}
