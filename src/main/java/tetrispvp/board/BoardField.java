package tetrispvp.board;


import java.awt.*;

public class BoardField implements GridField {
    private boolean isOccupied;
    private boolean isLocked;
    private Color color;
    private int powerUpID;

    public static BoardField GetEmptyBoardField() {
        return new BoardField();
    }

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
        return isOccupied;
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getPowerUpID() {
        return powerUpID;
    }
}
