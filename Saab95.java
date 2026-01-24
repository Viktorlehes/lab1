import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    public Saab95(){
        super(2, 125, 0, Color.red, "Saab95");
        turboOn = false;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return super.getEnginePower() * 0.01 * turbo;
    }

    @Override
    public void incrementSpeed(double amount){
        super.setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    @Override
    public void decrementSpeed(double amount){
        super.setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }
}
