package battery.vitals.model;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-03-02
 */
public class ThresholdValue {

    private String name;
    private float minimumValue;
    private float maximumValue;

    public ThresholdValue(
        String name,
        float minimumValue,
        float maximumValue
    )
    {
        this.name = name;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(float minimumValue) {
        this.minimumValue = minimumValue;
    }

    public float getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(float maximumValue) {
        this.maximumValue = maximumValue;
    }

    public AttributeState verify(float currentValue){
        if(currentValue < minimumValue ) {
            return AttributeState.UNDER;
        }
        if (currentValue > maximumValue){
            return AttributeState.OVER;
        }
        return AttributeState.OK;
    }
}
