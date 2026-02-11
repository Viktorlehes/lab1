import java.awt.*;

abstract public class Car extends Vehicle{
    private Carrier attachedTo;

    Car(int nrDoors,
        double enginePower,
        Color color,
        String modelName){
        super(nrDoors, enginePower, color, modelName);
    }

    public void attachTo(Carrier carrier) {
        super.lock();
        this.attachedTo = carrier;
    }

    public void detachFrom() {
        if (this.attachedTo == null) return;
        this.updatePosition(this.attachedTo.getPosition());
        super.unlock();
        this.attachedTo = null;
    }

    @Override
    public PointD getPosition() {
        if (attachedTo != null)
            return  attachedTo.getPosition();
        return super.getPosition();
    }
}