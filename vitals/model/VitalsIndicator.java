package vitals.model;

/**
 * @author Shrinidhi Muralidhar Karanam on 2021-03-01
 */
public class VitalsIndicator {

    private String name;
    private AttributeState status;

    private String message;

    public VitalsIndicator(
        String name,
        AttributeState status,
        String message
    )
    {
        this.name = name;
        this.status = status;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
