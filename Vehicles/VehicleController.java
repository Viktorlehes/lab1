package Vehicles;

public interface VehicleController {
    public void updatePosition(double x, double y);
    public void updatePosition(PointD p);
    public PointD getPosition();
    public void move();
    public void stopEngine();
    public void startEngine();
    public void turnLeft();
    public void turnRight();
    public void gas(double amount);
    public void brake(double amount);
    public boolean hasTurbo();
    public String getModelName();
}