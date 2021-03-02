package vitals;

import java.util.Collections;
import java.util.List;

import vitals.model.ThresholdValue;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-03-02
 */
class ThresholdProvider {
    private static final int MINIMUM_TEMPERATURE = 0;
    private static final int MAXIMUM_TEMPERATURE = 45;
    private static final int MINIMUM_SOC_VALUE = 20;
    private static final int MAXIMUM_SOC_VALUE = 80;
    private static final float MAXIMUM_CHANGE_RATE = 0.8f;

    private static final List<ThresholdValue> thresholdList = List.of(
        new ThresholdValue("Temperature", MINIMUM_TEMPERATURE, MAXIMUM_TEMPERATURE),
        new ThresholdValue("SOC", MINIMUM_SOC_VALUE, MAXIMUM_SOC_VALUE),
        new ThresholdValue("ChargeRate", 0, MAXIMUM_CHANGE_RATE)
    );

    private ThresholdProvider() {
    }

    public static ThresholdValue provideThreshold(String name) {
        return thresholdList.stream().filter(i -> name.equalsIgnoreCase(i.getName())).findFirst().orElse(null);
    }

}
