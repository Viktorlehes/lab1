import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Saab95Test {
    private final Saab95 saab = new Saab95();

    @Test
    @DisplayName("Check Nr Doors")
    void checkDoors() {
        Assertions.assertEquals(2, saab.getNrDoors());
    }

    @Test
    @DisplayName("Check model name")
    void checkModelName() {
        Assertions.assertEquals("Saab95", saab.getModelName());
    }

    @Test
    @DisplayName("Check gas within limit")
    void checkGasSafe() {
        saab.startEngine();
        saab.gas(2);
        Assertions.assertEquals(0,saab.getCurrentSpeed());
        for (int i=0; i<100; i++)
            saab.gas(1);
        Assertions.assertEquals(125,saab.getCurrentSpeed());
        saab.gas(1);
    }

    @Test
    @DisplayName("Check speedFactor")
    void checkTrim() {
        Assertions.assertEquals(1.25, saab.speedFactor());
    }

    @Test
    @DisplayName("Check turbo function")
    void checkTurbo() {
        Assertions.assertFalse(saab.getTurboStatus());
        saab.setTurboOn();
        Assertions.assertTrue(saab.getTurboStatus());
        saab.setTurboOff();
        Assertions.assertFalse(saab.getTurboStatus());
    }
}