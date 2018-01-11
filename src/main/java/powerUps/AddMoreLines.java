package powerUps;

import powerUps.mocks.FieldState;
import powerUps.mocks.GridField;

public class AddMoreLines implements PowerUp{
	
	int maxAllowedSimultaneousActivations = 2;
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
        PowerUpManager.getPowerUpManager().getMockNetwork().send("addLines", powerUpPresence);
	}
}
