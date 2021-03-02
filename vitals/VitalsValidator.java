package vitals;

import java.util.Map;

import vitals.model.AttributeState;
import vitals.model.ThresholdValue;
import vitals.model.VitalsIndicator;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-03-01
 */
public class VitalsValidator {


    public boolean checkBattery(Map<String, Float> batteryAttributes){
        return batteryAttributes.entrySet().stream()
            .map(this::check).allMatch(i -> i.getStatus() == AttributeState.OK);
    }

    private VitalsIndicator check(Map.Entry<String, Float> entry) {
        ThresholdValue value = ThresholdProvider.provideThreshold(entry.getKey());
        AttributeState status = value.verify(entry.getValue());
        if (status != AttributeState.OK) {
            System.out.println(entry.getKey() + " " + entry.getValue() + " is out of range and is " + status.toString().toLowerCase() + " provided threshold");
        }
        return new VitalsIndicator(entry.getKey(), status);
    }
}
