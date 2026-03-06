public class CarController {
    private final ApplicationModel model;

    CarController(ApplicationModel model) {
        this.model = model;
    }

    void gas(int gasAmount) {model.gas(gasAmount);}
    void brake(int gasAmount) {model.brake(gasAmount);}
    void startCars() {model.startCars();}
    void stopCars() { model.stopCars();}
    void turboOn() {model.turboOn();}
    void turboOff() {model.turboOff();}
    void raiseBed() {model.raiseBed();}
    void lowerBed() {model.lowerBed();}
    void addCar(int position) {model.addCar(position);}
    void removeCar() {model.removeCar();}
}
