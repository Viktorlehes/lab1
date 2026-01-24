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
        double currentSpeed,
        Color color,
        String modelName ){
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.currentSpeed = currentSpeed;
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
    public PointD getPosition() {return position;}
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }
    protected void setCurrentSpeed(double speed) { this.currentSpeed = speed;}

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

    public abstract double speedFactor();

    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    public void move() {
        switch (direction) {
            case UP -> position.y += currentSpeed;
            case RIGHT -> position.x += currentSpeed;
            case DOWN -> position.y -= currentSpeed;
            case LEFT -> position.x -= currentSpeed;
        }
    }

    public void turnLeft(){
        direction = direction.turnLeft();
    }

    public void  turnRight(){
        direction = direction.turnRight();
    }
}
