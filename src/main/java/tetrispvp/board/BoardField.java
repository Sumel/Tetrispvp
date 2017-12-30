package tetrispvp.board;


import java.awt.*;

public class BoardField implements GridField {
    private boolean isOccupied;
    private boolean isLocked;
    private Color color;
    private int powerUpID;

    public BoardField(boolean isOccupied, boolean isLocked, Color color,int powerUpID) {
        this.isOccupied = isOccupied;
        this.isLocked = isLocked;
        this.color = color;
        this.powerUpID = powerUpID;
    }

    public BoardField(){
        isOccupied = false;
        isLocked = false;
        color = null;
        powerUpID = -1;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int getPowerUpID() {
        return 0;
    }
}
