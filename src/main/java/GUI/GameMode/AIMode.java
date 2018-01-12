package GUI.GameMode;

public class AIMode implements GameMode {
    Mode gameType = Mode.AI;
    int level;

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
