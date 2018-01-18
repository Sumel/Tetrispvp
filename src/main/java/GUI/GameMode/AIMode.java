package GUI.GameMode;

public class AIMode implements GameMode {
    private Mode gameType = Mode.AI;
    private int level;

    public AIMode(int level) {
        this.level = level;
    }

    @Override
    public Mode getMode() {
        return gameType;
    }

    public int getLevel() {
        return level;
    }
}
