import java.util.ArrayList;

public class Verkstad<T extends Car> {
    private final ArrayList<T> inventory = new ArrayList<>();
    private final int maxInventory;

    public Verkstad (int maxInventory) {
        this.maxInventory = maxInventory;
    }

    /**
     * Adds a vehicle to the verkstad if there is space
     * @param car The vehicle to be added
     */
    public void addVehicle(T car) {
        if (inventory.size() < maxInventory)
            inventory.add(car);
    }

    /**
     * Takes a specified vehicle and returns it from inventory
     * to user if it exists, otherwise throws exception
     * @param vehicle Vehicle to be removed
     * @return The vehicle that was successfully removed
     */
    public T removeVehicle(T car) {
        if (inventory.contains(car)) return inventory.remove(inventory.indexOf(car));

        throw new IllegalStateException("Vehicle was not found in inventory");
    }
}