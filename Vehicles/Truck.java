package Vehicles;

import java.awt.*;

abstract public class Truck extends Vehicle {
    private boolean bedActive;
    Truck(int nrDoors,
        double enginePower,
        Color color,
        String modelName){
        super(nrDoors, enginePower, color, modelName);
        bedActive = false;
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    public boolean isBedActive() {
        return bedActive;
    }

    protected void setBedPosition(boolean bedActive) {
        this.bedActive = bedActive;
    }

    @Override
    public void move() {
        if (!bedActive)
            super.move();
    }
}