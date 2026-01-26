import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Saab95Test {
    private final Saab95 saab = new Saab95();

    @Test
    @DisplayName("Check Nr Doors")
    void checkDoors() {
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    @DisplayName("Check model name")
    void checkModelName() {
        assertEquals("Saab95", saab.getModelName());
    }

    @Test
    @DisplayName("Check gas within limit")
    void checkGasSafe() {
        saab.gas(2);
        assertEquals(0,saab.getCurrentSpeed());

        saab.incrementSpeed(130);
        assertEquals(125,saab.getCurrentSpeed());
        saab.gas(1);
    }

    @Test
    @DisplayName("Check speedFactor")
    void checkTrim() {
        assertEquals(1.25, saab.speedFactor());
    }

    @Test
    @DisplayName("Check turbo function")
    void checkTurbo() {
        assertFalse(saab.getTurboStatus());
        saab.setTurboOn();
        assertTrue(saab.getTurboStatus());
        saab.setTurboOff();
        assertFalse(saab.getTurboStatus());
    }
}