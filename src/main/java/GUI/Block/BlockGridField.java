package GUI.Block;

import javafx.scene.paint.Color;

public class BlockGridField implements GridField {

    private int powerUpId = -1;
    private Color color;
    private boolean isOccupied;
    private boolean isLocked;

    public BlockGridField(boolean isOccupied, Color color) {
        this.isOccupied = isOccupied;
        this.color = color;
    }

    public BlockGridField(boolean isOccupied, Color color, int powerUpId) {
        this(isOccupied, color);
        this.powerUpId = powerUpId;
    }

    public boolean hasPowerUp() {
        return powerUpId != -1;
    }

    public int getPowerUpID() {
        return powerUpId;
    }

    public void setPowerUpId(int powerUpId) {
        this.powerUpId = powerUpId;
    }

    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof GridField)) {
            return false;
        }
        GridField otherBoardField = (GridField) object;
        return isOccupied() == otherBoardField.isOccupied() &&
                isLocked() == otherBoardField.isLocked() &&
                (getColor() == null && otherBoardField.getColor() == null || getColor().equals(otherBoardField.getColor())) &&
                getPowerUpID() == otherBoardField.getPowerUpID();
    }
}
