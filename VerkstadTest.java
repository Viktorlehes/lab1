import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerkstadTest {
    private final Verkstad<Vehicle> vehVerkstad = new Verkstad<>(2);
    private final Verkstad<Car> carVerkstad = new Verkstad<>(1);
    private final Car volvo = new Volvo240();
    private final Car saab = new Saab95();
    private final Scania scania = new Scania(java.awt.Color.black);

    @Test
    @DisplayName("Add up to capacity")
    void addUpToCapacity() {
        vehVerkstad.addVehicle(volvo);
        vehVerkstad.addVehicle(scania);
        vehVerkstad.addVehicle(saab);

        Assertions.assertDoesNotThrow(() -> vehVerkstad.removeVehicle(volvo));
        Assertions.assertDoesNotThrow(() -> vehVerkstad.removeVehicle(scania));
        Assertions.assertThrows(Exception.class, () -> vehVerkstad.removeVehicle(saab));
    }

    @Test
    @DisplayName("Remove missing vehicle throws")
    void removeMissingVehicle() {
        Assertions.assertThrows(Exception.class, () -> vehVerkstad.removeVehicle(volvo));
    }

    @Test
    @DisplayName("Car-only verkstad accepts only cars")
    void carOnlyVerkstad() {
        carVerkstad.addVehicle(volvo);
        carVerkstad.addVehicle(saab);

        Assertions.assertDoesNotThrow(() -> carVerkstad.removeVehicle(volvo));
        Assertions.assertThrows(Exception.class, () -> carVerkstad.removeVehicle(saab));
    }
}
