package Vehicles;

import java.awt.*;

public class Scania extends Truck {
    private double bedAngle = 0;

    public Scania(Color color){
        super(2, 220, color, "Scania");
    }

    public double getBedAngle() {
        return bedAngle;
    }

    /**
    * Takes target angle between 0-70 degrees,
    * Vehicles.Truck needs to be standing still while ramp
    * is being raised and/or is raised
    * @param targetAngle target angle between 0-70 degrees
    * */
    public void raiseBed(double targetAngle){
        if (getCurrentSpeed() == 0 && targetAngle >bedAngle && targetAngle <= 70) {
            bedAngle = targetAngle;
            setBedPosition(true);
        }
    }

    /**
     * Takes target angle between 0-currentBedAngle degrees
     * @param targetAngle target angle between 0-currentBedAngle degrees
     * */
    public void lowerBed(double targetAngle){
        if (targetAngle >= 0 && targetAngle <= getBedAngle())
            bedAngle = targetAngle;
        if (bedAngle == 0)
            setBedPosition(false);
    }
}
