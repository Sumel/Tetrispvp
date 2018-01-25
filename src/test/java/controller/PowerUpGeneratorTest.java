package controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PowerUpGeneratorTest {

    @Test
    public void testIsPowerUpGeneratorSingleton()  {
        // given
        PowerUpGenerator firstPowerUpGenerator = PowerUpGenerator.getPowerUpGenerator();
        PowerUpGenerator secondPowerUpGenerator = PowerUpGenerator.getPowerUpGenerator();
        // then
        assertEquals(firstPowerUpGenerator, secondPowerUpGenerator);
    }

    @Test
    public void testRandPowerUpPosition() {
        // given
        PowerUpGenerator powerUpGenerator = PowerUpGenerator.getPowerUpGenerator();
        // when
        int powerUpPosition = powerUpGenerator.randPowerUpPosition();
        // then
        assertTrue(powerUpPosition >= 0 && powerUpPosition < 4);
    }

}
