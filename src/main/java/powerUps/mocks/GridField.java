package powerUps.mocks;

public class GridField {
	
	long powerUpId = -1;	// -1 = no PowerUp
	FieldState state = FieldState.EMPTY;

	public FieldState getState() {
		return state;
	}

	public void setState(FieldState state) {
		this.state = state;
	}

	public boolean hasPowerUp(){
		if(powerUpId == -1)
			return false;
		return true;
	}
	
	public long getPowerUp(){
		return powerUpId;
	}

	public void setPowerUp(long powerUpId) {
		this.powerUpId = powerUpId;
	}
	
	
	
}
