package Vehicles;

public interface VerkstadController<T extends Car> {
    void addVehicle(T car);
    T removeVehicle(T car);
}
