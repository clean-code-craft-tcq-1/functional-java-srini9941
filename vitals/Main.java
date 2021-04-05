package vitals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vitals.model.AttributeState;
import vitals.model.VitalsIndicator;

public class Main {

    static boolean batteryIsOk(
        float temperature,
        float soc,
        float chargeRate
    )
    {
        Map<String, Float> currentAttributeValues = new HashMap<>();
        currentAttributeValues.put("Temperature", temperature);
        currentAttributeValues.put("SOC", soc);
        currentAttributeValues.put("ChargeRate", chargeRate);
        VitalsValidator validator = new VitalsValidator();
        List<VitalsIndicator> batteryVitals = validator.getBatteryVitals(currentAttributeValues);
        return batteryVitals.stream().allMatch(i -> i.getStatus() == AttributeState.NORMAL);
    }

    public static void main(String[] args) {
        assert (batteryIsOk(25, 70, 0.7f) == true);
        assert (batteryIsOk(50, 85, 0.0f) == false);
        assert (batteryIsOk(25, 85, 0.6f) == false);
        assert (batteryIsOk(25, 60 , 0.9f) == false);
        assert (batteryIsOk(-10, 70, 0.6f) == false);
        assert (batteryIsOk(25, 10, 0.6f) == false);
        assert (batteryIsOk(25, 78, 0.7f) == false);
    }
}
