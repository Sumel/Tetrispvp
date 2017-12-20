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
}
