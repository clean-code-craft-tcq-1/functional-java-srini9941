package vitals;

import java.util.Map;
import java.util.function.BiConsumer;

import vitals.model.AttributeState;
import vitals.model.CompareOperator;
import vitals.model.ThresholdValue;
import vitals.model.VitalsIndicator;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-03-01
 */
public class VitalsValidator {

    private boolean anyConditionMatch = false;
    private String result;

    public boolean checkBattery(Map<String, Float> batteryAttributes) {
        return batteryAttributes.entrySet().stream()
            .map(this::check)
            .allMatch(i -> i.getStatus() == AttributeState.OK);
    }

    private VitalsIndicator check(Map.Entry<String, Float> entry) {
        ThresholdValue value = ThresholdProvider.provideThreshold(entry.getKey());
        String message = null;
        AttributeState status = null;
        checkFor(this::highBreach, entry.getValue(), value.getMaximumValue());
        checkFor(
            this::highWarning,
            entry.getValue(),
            value.getMaximumValue() - getFivePercentOf(value.getMaximumValue())
        );
        checkFor(this::lowBreach, entry.getValue(), value.getMinimumValue());
        checkFor(
            this::lowWarning,
            entry.getValue(),
            value.getMinimumValue() + getFivePercentOf(value.getMinimumValue())
        );
        if (!anyConditionMatch) {
            status = AttributeState.OK;
        }
        if (status != AttributeState.OK) {
            message = entry.getKey() + " " + entry.getValue() + " is " + result;
            System.out.println(message);
        }
        return new VitalsIndicator(entry.getKey(), status, message);
    }

    private void highBreach(
        float a,
        float b
    )
    {
        if (CompareOperator.GREATER.apply(a, b)) {
            result = "over provided threshold";
            anyConditionMatch = true;
        }
    }

    private void highWarning(
        float a,
        float b
    )
    {
        if (CompareOperator.GREATER.apply(a, b)) {
            result = "warning to breach high threshold";
            anyConditionMatch = true;
        }
    }

    private void lowBreach(
        float a,
        float b
    )
    {
        if (CompareOperator.LESSER.apply(a, b)) {
            result = "under provided threshold";
            anyConditionMatch = true;
        }
    }

    private void lowWarning(
        float a,
        float b
    )
    {
        if (CompareOperator.LESSER.apply(a, b)) {
            result = "warning to breach low threshold";
            anyConditionMatch = true;
        }
    }

    private void checkFor(
        BiConsumer<Float, Float> consumer,
        float t,
        float u
    )
    {
        if (!anyConditionMatch) {
            consumer.accept(t, u);
        }
    }

    private float getFivePercentOf(float number) {
        return (float) (number * 0.05);
    }
}
