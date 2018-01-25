package controller;

import controller.mocks.Block;

public class PowerUpBlockProvider {

    private static PowerUpBlockProvider powerUpBlockProvider = new PowerUpBlockProvider();

    private final PowerUpGenerator powerUpGenerator;
    private final BlockGenerator blockGenerator;

    private PowerUpBlockProvider() {
        this.powerUpGenerator = PowerUpGenerator.getPowerUpGenerator();
        this.blockGenerator = BlockGenerator.getBlockGenerator();
    }

    public static PowerUpBlockProvider getPowerUpBlockProvider() {
        return powerUpBlockProvider;
    }

    public Block getBlockWithPowerUp() {
        Block block = blockGenerator.spawnBlock();
        int powerUpPosition = powerUpGenerator.randPowerUpPosition();
        int powerUp = powerUpGenerator.nextRandomPowerUp();
        block.setPowerUp(powerUpPosition, powerUp);
        return block;
    }

}
