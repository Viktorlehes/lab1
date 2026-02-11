import java.util.ArrayList;

public class Verkstad<T extends Vehicle> {
    private final ArrayList<T> inventory = new ArrayList<>();
    private final int maxInventory;

    public Verkstad (int maxInventory) {
        this.maxInventory = maxInventory;
    }

    /**
     * Adds a vehicle to the verkstad if there is space
     * @param vehicle The vehicle to be added
     */
    public void addVehicle(T vehicle) {
        if (inventory.size() < maxInventory)
            inventory.add(vehicle);
    }

    /**
     * Takes a specified vehicle and returns it from inventory
     * to user if it exists, otherwise throws exception
     * @param vehicle Vehicle to be removed
     * @return The vehicle that was successfully removed
     * @throws Exception Vehicle does not exist in the Verkstad
     */
    public T removeVehicle(T vehicle) throws Exception {
        if (inventory.contains(vehicle)) return inventory.remove(inventory.indexOf(vehicle));

        throw new Exception("Vehicle was not found in inventory");
    }
}