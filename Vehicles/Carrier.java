package Vehicles;

import java.awt.*;
import java.util.LinkedList;

public class Carrier extends Truck {
    private final int maxLoad;
    private final LinkedList<Car> load;

    Carrier(Color color, int maxLoad){
        super(2, 225, color, "Carrier");
        this.maxLoad = maxLoad;
        this.load = new LinkedList<>();
    }

    /**
     * Gets the current number of cars on the carrier
     * @return int num cars on carrier
     */
    public int getLoad() {
        return load.size();
    }

    @Override
    public void move() {
        super.move();
        for (Vehicles.Car car : load) {
            car.updatePosition(this.getPosition());
        }
    }

    /**
     * Raise the ramp so truck can resume driving
     */
    public void raiseRamp() {
        setBedPosition(false);
    }

    /**
     * Attempts to lower the ramp for cars to load and unload.
     * Vehicles.Truck needs to be standing still
     */
    public void lowerRamp() {
        if (getCurrentSpeed() == 0)
            setBedPosition(true);
    }

    public void loadCar(Car car) {
        if (load.contains(car)) return;

        double dx = this.getPosition().x - car.getPosition().x;
        double dy = this.getPosition().y - car.getPosition().y;
        boolean withinRange = dx*dx + dy*dy <= 25;

        if (isBedActive() && load.size() < maxLoad && withinRange) {
            load.addLast(car);
        }
    }

    public Car ofLoadCar() throws Exception {
        if (isBedActive() && !load.isEmpty()) {
            return load.removeLast();
        }
        throw new Exception("Bed needs to be lowered to offload Vehicles.Car");
    }
}
