import Vehicles.*;

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ApplicationModel {
    // CONFIG
    private int delay;

    // FIELDS
    private final List<VehicleController> vehicles;
    private VerkstadController<Car> verkstad;
    private final List<ApplicationListener> observers = new ArrayList<>();

    public ApplicationModel(List<VehicleController> vehicles, int delay) {
        this.vehicles = vehicles;
        this.verkstad = new Verkstad<Car>(1);
        this.delay = delay;
        startGame();
    }

    private void startGame() {
        Timer timer = new Timer(delay, new TimerListener());
        timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (VehicleController vehicle : vehicles) {
                moveVehicleInBounds(vehicle);

                if (isInsideWorkshop(vehicle.getPosition()) && vehicle instanceof Volvo240 volvo) {
                    try {
                        verkstad.addVehicle(volvo);
                        vehicles.remove(volvo);
                        onRemoval(volvo);
                        return;
                    } catch (Exception ignored) {}
                }
            notifyObservers();
            }
        }
    }

    private void notifyObservers() {
        Map<String, PointD> vehicleData = new HashMap<>();
        vehicles.forEach(v ->
            vehicleData.put(v.getModelName(), v.getPosition()));

        for (ApplicationListener observer : observers) {
            observer.onUpdate(vehicleData);
        }
    }

    void onRemoval(VehicleController vehicle) {
        for (ApplicationListener observer : observers) {
            observer.onRemoval(vehicle.getModelName());
        }
    }

    public void addObserver(ApplicationListener observer) {
        observers.add(observer);
    }

    public void removeObserver(ApplicationListener observer) {
        observers.remove(observer);
    }

    void gas(int amount) {vehicles.forEach(v -> v.gas(((double) amount) / 100));}
    void brake(int amount) {vehicles.forEach(v -> v.brake(((double) amount) / 100));}
    void startCars() {vehicles.forEach( VehicleController::startEngine);}
    void stopCars() {vehicles.forEach( VehicleController::stopEngine);}

    void turboOn() {
        for  (VehicleController vehicle : vehicles) {
            if (vehicle.hasTurbo()) {
                VehicleWithTurbo vehicleWithTurbo = (VehicleWithTurbo)vehicle;
                vehicleWithTurbo.setTurboOn();
            }
        }
    }

    void turboOff() {
        for  (VehicleController vehicle : vehicles) {
            if (vehicle.hasTurbo()) {
                VehicleWithTurbo vehicleWithTurbo = (VehicleWithTurbo)vehicle;
                vehicleWithTurbo.setTurboOff();
            }
        }
    }

    void raiseBed() {
        for  (VehicleController vehicle : vehicles) {
            if (vehicle.getModelName().equals("Scania")) {
                Scania scania = (Scania)vehicle;
                scania.raiseBed(70);
            }
        }
    }

    void lowerBed() {
        for (VehicleController vehicle : vehicles) {
            if (vehicle.getModelName().equals("Scania")) {
                Scania scania = (Scania)vehicle;
                scania.lowerBed(0);
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

    private void moveVehicleInBounds(VehicleController vehicle) {
        PointD pos_before = vehicle.getPosition();
        vehicle.move();
        PointD pos_after = vehicle.getPosition();
        if (isOutOfBounds(pos_after)) {
            handleOutOfBounds(vehicle);
            vehicle.updatePosition(pos_before);
        }
    }

    private void handleOutOfBounds(VehicleController vehicle) {
        vehicle.stopEngine();
        vehicle.turnLeft();
        vehicle.turnLeft();
        vehicle.startEngine();
    }

    private boolean isOutOfBounds(PointD pos) {
        return (pos.x < 0 || pos.y < 0 || pos.x > CarView.DRAWPANELWIDTH - 100
                || pos.y > CarView.DRAWPANELHEIGHT - 60);
    }

    static void main() {
        VehicleController volvo = new Volvo240();
        VehicleController saab = new Saab95();
        VehicleController scania = new Scania(Color.black);
        volvo.updatePosition(0,300);
        saab.updatePosition(0,100.0);
        scania.updatePosition(0,200.0);
        List<VehicleController> vehicles = new ArrayList<>();
        vehicles.add(volvo);
        vehicles.add(saab);
        vehicles.add(scania);
        ApplicationModel m = new ApplicationModel(vehicles, 10);
        CarController2 cc  = new CarController2(m);
        CarView v = new CarView("CarSim 1.0", cc);

        m.addObserver(v);
    }
}