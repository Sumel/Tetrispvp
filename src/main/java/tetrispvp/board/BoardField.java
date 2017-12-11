package tetrispvp.board;

import tetrispvp.board.Mocks.PowerUp;

import java.awt.*;
import java.util.Optional;

public interface BoardField {
    /**
     * Returns information about occupation state of this BoardField.
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
     * Gets the color of this field. Should only be used if the field is occupied.
     *
     * @return Returns the color of this field.
     */
    Color getColor();

    /**
     * Used to get potential power up strategy for this field.
     *
     * @return Returns this field's PowerUp if set.
     */
    Optional<PowerUp> getPowerUp();

    /**
     * Used to determine whether this field has a power up attached.
     *
     * @return Returns true if this field has a power up attached. Returns false otherwise.
     */
    default boolean hasPowerUp() {
        return getPowerUp().isPresent();
    }
}
