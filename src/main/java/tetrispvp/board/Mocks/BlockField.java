package tetrispvp.board.Mocks;

import tetrispvp.board.GridField;

import java.awt.*;
import java.util.Optional;

public class BlockField implements GridField {

    private final boolean isOccupied;
    private final Color color;
    private final int powerUp;

    public BlockField(boolean isOccupied, Color color, int powerUp) {
        this.isOccupied = isOccupied;
        this.color = color;
        this.powerUp = powerUp;
    }

    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getPowerUpID() {
        return powerUp;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof GridField)) {
            return false;
        }
        GridField otherBoardField = (GridField) object;
        return isOccupied() == otherBoardField.isOccupied() &&
                isLocked() == otherBoardField.isLocked() &&
                getColor().equals(otherBoardField.getColor()) &&
                getPowerUpID() == otherBoardField.getPowerUpID();
    }
}
