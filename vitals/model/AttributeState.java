package vitals.model;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-03-02
 */
public enum AttributeState {

    OVER(AttributeStateCategory.CRITICAL),
    WARNING_TO_HIGH_BREACH(AttributeStateCategory.WARNING),
    UNDER(AttributeStateCategory.CRITICAL),
    WARNING_TO_LOW_BREACH(AttributeStateCategory.WARNING),
    NORMAL(AttributeStateCategory.NORMAL);

    private final AttributeStateCategory category;

    AttributeState (AttributeStateCategory category) {
        this.category = category;
    }

    public AttributeStateCategory getCategory () {
        return category;
    }
}
