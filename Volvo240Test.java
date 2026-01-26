import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Volvo240Test {
    private final Volvo240 volvo = new Volvo240();

    @Test
    @DisplayName("Check Nr Doors")
    void checkDoors() {
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    @DisplayName("Check model name")
    void checkModelName() {
        assertEquals("Volvo240", volvo.getModelName());
    }

    @Test
    @DisplayName("Check gas within limit")
    void checkGasSafe() {
        volvo.gas(2);
        assertEquals(0,volvo.getCurrentSpeed());

        volvo.incrementSpeed(101);
        assertEquals(100,volvo.getCurrentSpeed());
        volvo.gas(1);
    }

    @Test
    @DisplayName("Check speedFactor")
    void checkTrim() {
        assertEquals(1.25, volvo.speedFactor());
    }

    @Test
    @DisplayName("Check brake wihing limit")
    void checkBrakeSafe() {
        volvo.brake(2);
        assertEquals(0, volvo.getCurrentSpeed());
        volvo.brake(1);
        assertEquals(0, volvo.getCurrentSpeed());
        volvo.gas(1);
        volvo.brake(1);
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    @DisplayName("Movement")
    void checkMovement() {
        assertEquals(0, volvo.getPosition().x);
        assertEquals(0, volvo.getPosition().y);

        
    }
}