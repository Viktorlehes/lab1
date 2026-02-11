import java.awt.*;

abstract public class Vehicle implements Movable {
    private final int nrDoors;
    private final double enginePower;
    private boolean engineRunning;
    private double currentSpeed;
    private final Color color;
    private final String modelName;
    private final PointD position;
    private Direction direction;
    private boolean isLocked;

    Vehicle(int nrDoors,
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

    protected void lock() {isLocked=true;}
    protected void unlock() {isLocked=false;}

    public int getNrDoors(){
        return nrDoors;
    }
    public String getModelName() {return modelName;}
    public PointD getPosition() {
        return new PointD(position);
    }

    protected  void updatePosition(PointD p) {this.updatePosition(p.x,p.y);}

    protected void updatePosition(double x, double y) {
        double dx = this.getPosition().x - x;
        double dy = this.getPosition().y - y;
        boolean reasonableRange = dx*dx + dy*dy <= 1000;   // Update according to speed ??

        if (reasonableRange) {
            position.x = x;
            position.y = y;
        }
    }

    public double getEnginePower(){
        return enginePower;
    }
    public boolean isEngineRunning() {
        return engineRunning;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void startEngine(){currentSpeed = 0; engineRunning = true;}
    public void stopEngine(){currentSpeed = 0; engineRunning = false;}

    protected abstract double speedFactor();

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    @Override
    public void move() {
        if (engineRunning && !isLocked) {
            switch (direction) {
                case UP -> position.y += currentSpeed;
                case RIGHT -> position.x += currentSpeed;
                case DOWN -> position.y -= currentSpeed;
                case LEFT -> position.x -= currentSpeed;
            }
        }
    }

    @Override
    public void turnLeft(){
        direction = direction.turnLeft();
    }

    @Override
    public void turnRight(){
        direction = direction.turnRight();
    }

    public void gas(double amount){
        if (amount <= 1 && amount >= 0 && engineRunning)
            incrementSpeed(amount);
    }

    public void brake(double amount){
        if (amount <= 1 && amount >= 0)
            decrementSpeed(amount);
    }
}
