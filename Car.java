import java.awt.*;

abstract public class Car implements Movable {
    private final int nrDoors;
    private final double enginePower;
    private double currentSpeed;
    private Color color;
    private final String modelName;
    private final PointD position;
    private Direction direction;

    Car(int nrDoors,
        double enginePower,
        Color color,
        String modelName ){
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.position = new PointD(0.0,0.0);
        this.direction = Direction.DOWN;
        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public String getModelName() {return modelName;}
    public PointD getPosition() {
        return new PointD(position);
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }
    protected void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){currentSpeed = 0.1;}
    public void stopEngine(){
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    @Override
    public void move() {
        switch (direction) {
            case UP -> position.y += currentSpeed;
            case RIGHT -> position.x += currentSpeed;
            case DOWN -> position.y -= currentSpeed;
            case LEFT -> position.x -= currentSpeed;
        }
    }

    @Override
    public void turnLeft(){
        direction = direction.turnLeft();
    }

    @Override
    public void  turnRight(){
        direction = direction.turnRight();
    }

    public void gas(double amount){
        if (amount <= 1 && amount >= 0 && currentSpeed != 0)
            incrementSpeed(amount);
    }

    public void brake(double amount){
        if (amount <= 1 && amount >= 0 && currentSpeed != 0)
            decrementSpeed(amount);
    }
}
