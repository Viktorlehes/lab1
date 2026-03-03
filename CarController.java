import Vehicles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 10;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    protected final ArrayList<Vehicle> cars = new ArrayList<>();
    private final Verkstad<Volvo240> verkstad = new Verkstad<>(1);

    //methods:
    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        Vehicle volvo = new Volvo240();
        Vehicle saab = new Saab95();
        Vehicle scania = new Scania(Color.black);
        volvo.updatePosition(0,300);
        saab.updatePosition(0,100.0);
        scania.updatePosition(0,200.0);
        cc.cars.add(volvo);
        cc.cars.add(saab);
        cc.cars.add(scania);

        // Start a new view and send a reference of self
        //cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                PointD pos_before = car.getPosition();
                car.move();
                PointD pos_after = car.getPosition();
                if (isOutOfBounds(pos_after)) {
                    handleOutOfBounds(car);
                    car.updatePosition(pos_before);
                    pos_after = pos_before;
                }

                if (isInsideWorkshop(pos_after) && car instanceof Volvo240 volvo) {
                    try {
                        verkstad.addVehicle(volvo);
                        cars.remove(volvo);
                        frame.drawPanel.removeObject(volvo.getModelName());
                        frame.drawPanel.repaint();
                        return;
                    } catch (Exception ignored) {}
                }

                //frame.drawPanel.moveit(car.getModelName(), (int) pos_after.x, (int) pos_after.y);
                //repaint() //calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    boolean isInsideWorkshop(PointD pos) {
        int verkstadX = 300;
        int verkstadY = 300;
        int verkstadWidth = 101;
        int verkstadHeight = 96;
        return pos.x < verkstadX + verkstadWidth &&
                pos.x + 100 > verkstadX &&      // 100 = car width
                pos.y < verkstadY + verkstadHeight &&
                pos.y + 60 > verkstadY;         // 60 = car height
    }

    void handleOutOfBounds(Vehicle car) {
        car.stopEngine();
        car.turnLeft();
        car.turnLeft();
        car.startEngine();
    }

    boolean isOutOfBounds(PointD pos) {
        // X = 800px Y = 560px
        // Image : 100x60px
        return (pos.x < 0 || pos.y < 0 || pos.x > frame.drawPanel.getWidth() - 100
                || pos.y > frame.drawPanel.getHeight() - 60);
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Vehicle car : cars) {
            car.gas(gas);
       }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void startCars() {
        for (Vehicle vehicle : cars) {
            vehicle.startEngine();
        }
    }

    void stopCars() {
        for (Vehicle vehicle : cars) {
            vehicle.stopEngine();
        }
    }

    void turboOn() {
        for  (Vehicle vehicle : cars) {
            if (vehicle.hasTurbo()) {
                VehicleWithTurbo vehicleWithTurbo = (VehicleWithTurbo)vehicle;
                vehicleWithTurbo.setTurboOn();
            }
        }
    }

    void turboOff() {
        for  (Vehicle vehicle : cars) {
            if (vehicle.hasTurbo()) {
                VehicleWithTurbo vehicleWithTurbo = (VehicleWithTurbo)vehicle;
                vehicleWithTurbo.setTurboOff();
            }
        }
    }

    void raiseBed() {
        for  (Vehicle vehicle : cars) {
            if (vehicle.getModelName().equals("Vehicles.Scania")) {
                Scania scania = (Scania)vehicle;
                scania.raiseBed(70);
            }
        }
    }

    void lowerBed() {
        for  (Vehicle vehicle : cars) {
            if (vehicle.getModelName().equals("Vehicles.Scania")) {
                Scania scania = (Scania)vehicle;
                scania.lowerBed(0);
            }
        }
    }
}