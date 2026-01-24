import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnitTests {
    private final Volvo240 volvo = new Volvo240();
    private final Saab95 saab = new Saab95();

    @Test
    @DisplayName("Add two numbers")
    void startSpeed() {
        assertEquals(0, volvo.getCurrentSpeed());
        assertTrue(volvo.getEnginePower() < saab.getEnginePower());
    }

}