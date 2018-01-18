package mocks;

import java.util.Optional;

public class BlockField {

    private Optional<PowerUp> powerUp;

    public BlockField() {

    }

    public void setPowerUp(PowerUp powerUp){
        this.powerUp = Optional.ofNullable(powerUp);
    }
}
