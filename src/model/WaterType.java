package model;

/**
 * Created by Valerie on 10/11/2016.
 */
public enum WaterType {
    BOTTLED ("Bottled"),
    WELL ("Well"),
    SPRING ("Spring"),
    STREAM ("Stream"),
    LAKE ("Lake"),
    OTHER ("Other");

    /** the full string representation of the user type */
    private final String type;


    /**
     * Constructor for the enumeration
     *
     * @param type   the water type
     */
    WaterType(String type) {
        this.type = type;
    }

    /**
     *
     * @return   the water type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return the display string representation
     */
    public String toString() {
        return type;
    }


}
