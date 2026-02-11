import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TruckTest {
    private Carrier carrier = new Carrier(Color.black,7);
    private Scania scania = new Scania(Color.black);
    private Car car = new Volvo240();
    private Car car2 = new Saab95();
    private Verkstad<Vehicle> vehVerkstad = new Verkstad<>(5);
    private Verkstad<Car> carVerkstad = new Verkstad<>(5);

    @Test
    @DisplayName("Bed Positions")
    void testBedPositions() {
        Assertions.assertFalse(carrier.isBedActive());
        Assertions.assertEquals(0,scania.getBedAngle());
        carrier.lowerRamp();
        scania.startEngine();
        scania.gas(1);
        scania.raiseBed(30);
        Assertions.assertTrue(carrier.isBedActive());
        Assertions.assertEquals(0, scania.getBedAngle());
        scania.stopEngine();
        scania.raiseBed(30);
        Assertions.assertEquals(30, scania.getBedAngle());

        carrier.raiseRamp();
        carrier.startEngine();
        carrier.gas(1);
        carrier.lowerRamp();
        Assertions.assertFalse( carrier.isBedActive());
    }

    @Test
    @DisplayName("Car in vicinity")
    void testLoadUnloading() {
        car.updatePosition(6,6);
        carrier.updatePosition(0,0);

        carrier.stopEngine();
        carrier.lowerRamp();
        carrier.loadCar(car);
        Assertions.assertEquals(0, carrier.getLoad());
        car.updatePosition(4,3);
        carrier.loadCar(car);
        Assertions.assertEquals(1, carrier.getLoad());
    }

    @Test
    @DisplayName("Loaded Cars move with Carrier")
    void carsOnCarrier() {
        carrier.lowerRamp();
        carrier.loadCar(car);
        carrier.loadCar(car2);
        carrier.loadCar(car);

        Assertions.assertEquals(2,carrier.getLoad());

        carrier.raiseRamp();
        carrier.startEngine();
        carrier.gas(1);
        carrier.move();
        carrier.turnLeft();
        carrier.move();

        Assertions.assertEquals(2.25, car.getPosition().x);
        Assertions.assertEquals(-2.25, car.getPosition().y);
        Assertions.assertEquals(2.25, car2.getPosition().x);
        Assertions.assertEquals(-2.25, car2.getPosition().y);
    }

    @Test
    @DisplayName("Verkstad")
    void verkstadTest() {
        vehVerkstad.addVehicle(car);
        vehVerkstad.addVehicle(scania);
        //Assertions.assertTcarVerkstad.addVehicle(scania);
    }

    @Test
    @DisplayName("Carrier load capacity is enforced")
    void testCarrierCapacity() {
        Carrier smallCarrier = new Carrier(Color.black, 2);
        Car c1 = new Volvo240();
        Car c2 = new Saab95();
        Car c3 = new Volvo240();

        smallCarrier.lowerRamp();
        smallCarrier.loadCar(c1);
        smallCarrier.loadCar(c2);
        smallCarrier.loadCar(c3);

        Assertions.assertEquals(2, smallCarrier.getLoad());
    }

    @Test
    @DisplayName("Carrier cannot lower ramp while moving")
    void testLowerRampWhileMoving() {
        carrier.raiseRamp();
        carrier.startEngine();
        carrier.gas(1);
        carrier.lowerRamp();
        Assertions.assertFalse(carrier.isBedActive());
    }

    @Test
    @DisplayName("Carrier offload requires lowered ramp or stops")
    void testOffloadBehavior() {
        carrier.lowerRamp();
        carrier.loadCar(car);
        carrier.raiseRamp();

        Assertions.assertThrows(Exception.class, () -> carrier.ofLoadCar());

        carrier.lowerRamp();
        Car removed = Assertions.assertDoesNotThrow(() -> carrier.ofLoadCar());
        Assertions.assertNotNull(removed);
        Assertions.assertEquals(0, carrier.getLoad());
    }

    @Test
    @DisplayName("Scania bed angle boundaries")
    void testScaniaBedAngleLimits() {
        scania.raiseBed(80);
        Assertions.assertEquals(0, scania.getBedAngle());

        scania.raiseBed(30);
        Assertions.assertEquals(30, scania.getBedAngle());
        scania.lowerBed(0);
        Assertions.assertEquals(0, scania.getBedAngle());
        Assertions.assertFalse(scania.isBedActive());
    }
}
