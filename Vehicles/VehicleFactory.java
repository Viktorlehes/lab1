package Vehicles;

import java.awt.*;

final public class VehicleFactory {

    static public Volvo240  createVolvo240(int x, int y) {
        Volvo240 volvo = new Volvo240();
        volvo.updatePosition(x,y);
        return volvo;
    }

    static public Saab95 createSaab95(int x, int y) {
        Saab95 saab = new Saab95();
        saab.updatePosition(x,y);
        return saab;
    }

    static public Scania createScania(int x, int y, Color color) {
        Scania scania = new Scania(color);
        scania.updatePosition(x,y);
        return scania;
    }
}
