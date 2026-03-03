package Vehicles;

import java.util.Map;

public interface ApplicationListener {
    public void onUpdate(Map<String, PointD> vehicleData);
    public void onRemoval(String carName);
}
