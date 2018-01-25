package GUI.GameMode;

public class AIMode implements GameMode {
    private Mode gameType = Mode.AI;
    private double level;

    public AIMode(double level) {
        this.level = level;
    }

    @Override
    public Mode getMode() {
        return gameType;
    }

    public double getLevel() {
        return level;
    }
}
