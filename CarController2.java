public class CarController2 {
    ApplicationModel model;

    CarController2(ApplicationModel model) {
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
}
