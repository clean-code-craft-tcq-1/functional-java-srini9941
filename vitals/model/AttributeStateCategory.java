package vitals.model;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-04-06
 */
public enum AttributeStateCategory {

    CRITICAL(2),
    WARNING(1),
    NORMAL(0);

    private final int value;

    AttributeStateCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
