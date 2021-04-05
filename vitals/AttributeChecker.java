package vitals;

import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import vitals.model.AttributeState;
import vitals.model.CompareOperator;
import vitals.model.ThresholdValue;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-04-05
 */
public class AttributeChecker {

    public static final double PERCENTAGE = 0.05;
    private final ThresholdValue thresholdValue;
    private final ResourceBundle messages;

    private boolean anyConditionMatch = false;
    private String result;
    private AttributeState attributeState;

    public AttributeChecker(
        ThresholdValue thresholdValue,
        ResourceBundle messages
    ) {
        this.thresholdValue = thresholdValue;
        this.messages = messages;
    }

    public String getResult() {
        return result;
    }

    public AttributeState getAttributeState() {
        return attributeState;
    }


    public void check(float currentValue){
        checkFor(this::highBreach, currentValue, thresholdValue.getMaximumValue());
        checkFor(
            this::highWarning,
            currentValue,
            thresholdValue.getMaximumValue() - getFivePercentOf(thresholdValue.getMaximumValue())
        );
        checkFor(this::lowBreach, currentValue, thresholdValue.getMinimumValue());
        checkFor(
            this::lowWarning,
            currentValue,
            thresholdValue.getMinimumValue() + getFivePercentOf(thresholdValue.getMinimumValue())
        );
        if (!anyConditionMatch) {
            attributeState = AttributeState.NORMAL;
        }
    }

    private void highBreach(
        float a,
        float b
    )
    {
        if (CompareOperator.GREATER.apply(a, b)) {
            result = messages.getString("OVER_PROVIDED_THRESHOLD");
            anyConditionMatch = true;
            attributeState = AttributeState.OVER;
        }
    }

    private void highWarning(
        float a,
        float b
    )
    {
        if (CompareOperator.GREATER.apply(a, b)) {
            result = messages.getString("WARNING_HIGH_BREACH");
            anyConditionMatch = true;
            attributeState = AttributeState.WARNING_TO_HIGH_BREACH;
        }
    }

    private void lowBreach(
        float a,
        float b
    )
    {
        if (CompareOperator.LESSER.apply(a, b)) {
            result = messages.getString("UNDER_PROVIDED_THRESHOLD");
            anyConditionMatch = true;
            attributeState = AttributeState.UNDER;
        }
    }

    private void lowWarning(
        float a,
        float b
    )
    {
        if (CompareOperator.LESSER.apply(a, b)) {
            result = messages.getString("WARNING_LOW_THRESHOLD");
            anyConditionMatch = true;
            attributeState = AttributeState.WARNING_TO_LOW_BREACH;
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
        return (float) (number * PERCENTAGE);
    }
}
