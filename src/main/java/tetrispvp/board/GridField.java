package tetrispvp.board;

import java.awt.*;

public interface GridField {
    /**
     * Returns information about occupation state of this GridField.
     *
     * @return Returns true if there is currently a field at this position (regardless if it's locked in place, or if it's the current block). Returns false otherwise.
     */
    boolean isOccupied();

    /**
     * Returns information about whether this field is locked on the board (no longer a part of BlockMover's block).
     *
     * @return Returns true if this field isn't a part of BlockMover's current block. Returns false otherwise.
     */
    boolean isLocked();

    /**
     * Returns information about whether this field can be cleared (grey lines cannot be cleared).
     *
     * @return Returns true if this field can be cleared. Returns false otherwise.
     */
    default boolean canBeCleared() {
        return true;
    }

    /**
     * Gets the color of this field. Should only be used if the field is occupied.
     *
     * @return Returns the color of this field.
     */
    Color getColor();

    /**
     * Used to get potential power up strategy ID for this field. Calling the strategy implementation is handled by PowerUpManager.
     *
     * @return Returns this field's PowerUp ID.
     */
    default int getPowerUpID() {
        return -1;
    }

    /**
     * Used to determine whether this field has a power up attached.
     *
     * @return Returns true if this field has a power up attached. Returns false otherwise.
     */
    default boolean hasPowerUp() {
        return getPowerUpID() != -1;
    }
}
