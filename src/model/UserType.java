package model;

/**
 * Created by sang on 10/1/16.
 * This enumeration represents all the user titles
 */
public enum UserType {
    USER ("User"),
    ADMIN ("Admin"),
    WORKER ("Worker"),
    MANAGER ("Manager");

    /** the full string representation of the user type */
    private final String type;


    /**
     * Constructor for the enumeration
     *
     * @param type   the user type
     */
    UserType(String type) {
        this.type = type;
    }

    /**
     *
     * @return   the user type
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
