import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Volvo240Test {
    private final Volvo240 volvo = new Volvo240();

    @Test
    @DisplayName("Check Nr Doors")
    void checkDoors() {
        Assertions.assertEquals(4, volvo.getNrDoors());
    }

    @Test
    @DisplayName("Check model name")
    void checkModelName() {
        Assertions.assertEquals("Volvo240", volvo.getModelName());
    }

    @Test
    @DisplayName("Check gas within limit")
    void checkGasSafe() {
        volvo.startEngine();
        Assertions.assertEquals(0.1,volvo.getCurrentSpeed());
        for (int i=0; i<100; i++)
            volvo.gas(1);
        Assertions.assertEquals(100,volvo.getCurrentSpeed());
    }

    @Test
    @DisplayName("Check speedFactor")
    void checkTrim() {
        Assertions.assertEquals(1.25, volvo.speedFactor());
    }

    @Test
    @DisplayName("Check brake wihing limit")
    void checkBrakeSafe() {
        volvo.brake(2);
        Assertions.assertEquals(0, volvo.getCurrentSpeed());
        volvo.brake(1);
        Assertions.assertEquals(0, volvo.getCurrentSpeed());
        volvo.gas(1);
        volvo.brake(1);
        Assertions.assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    @DisplayName("Check engine status")
    void checkEngineStatus(){
        volvo.stopEngine();
        Assertions.assertEquals(0,volvo.getCurrentSpeed());
        volvo.gas(1);
        Assertions.assertEquals(0,volvo.getCurrentSpeed());
        volvo.startEngine();
        volvo.gas(1);
        Assertions.assertEquals(1.35,volvo.getCurrentSpeed());
    }

    @Test
    @DisplayName("Movement")
    void checkMovement() {
        volvo.stopEngine();
        volvo.startEngine();
        Assertions.assertEquals(0, volvo.getPosition().x);
        Assertions.assertEquals(0, volvo.getPosition().y);
        volvo.gas(1);
        volvo.gas(1);
        volvo.gas(1);
        volvo.move();
        Assertions.assertEquals(0,volvo.getPosition().x);
        Assertions.assertEquals(-3.85, volvo.getPosition().y);
        volvo.turnRight();
        volvo.move();
        Assertions.assertEquals(-3.85, volvo.getPosition().x);
        Assertions.assertEquals(-3.85,volvo.getPosition().y);
        volvo.turnLeft();
        volvo.turnLeft();
        volvo.move();
        Assertions.assertEquals(0,volvo.getPosition().x);
        Assertions.assertEquals(-3.85,volvo.getPosition().y);
    }
}