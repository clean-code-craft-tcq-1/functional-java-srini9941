package vitals.model;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-04-05
 */
public enum CompareOperator {
    GREATER {
        @Override
        public boolean apply(
            float a,
            float b
        )
        {
            return a > b;
        }
    },
    LESSER {
        @Override
        public boolean apply(
            float a,
            float b
        )
        {
            return a < b;
        }
    },
    EQUALS {
        @Override
        public boolean apply(
            float a,
            float b
        )
        {
            return a == b;
        }
    };

    public abstract boolean apply(
        float a,
        float b
    );
}
