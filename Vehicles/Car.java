package Vehicles;

import java.awt.*;

abstract public class Car extends Vehicle {
    Car(int nrDoors,
        double enginePower,
        Color color,
        String modelName){
        super(nrDoors, enginePower, color, modelName);
    }
}