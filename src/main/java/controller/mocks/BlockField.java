package controller.mocks;

import java.util.Optional;

public class BlockField {

    private Optional<Integer> powerUp;

    public BlockField() {

    }

    public void setPowerUp(int powerUp){
        this.powerUp = Optional.ofNullable(powerUp);
    }
}
