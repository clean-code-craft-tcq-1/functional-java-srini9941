package battery.vitals.model;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-03-01
 */
public class VitalsIndicator {

    private String name;
    private AttributeState status;

    public VitalsIndicator(
        String name,
        AttributeState status
    )
    {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributeState getStatus() {
        return status;
    }

    public void setStatus(AttributeState status) {
        this.status = status;
    }

}
