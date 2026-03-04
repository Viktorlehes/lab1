import Vehicles.PointD;

import java.util.Map;

public interface ApplicationListener {
    public void onUpdate(Map<String, Pair<PointD,String>> vehicleData);
    public void onRemoval(String carName);
}
