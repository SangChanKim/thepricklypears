package model;

/**
 * Created by sang on 10/1/16.
 * This enumeration represents all the user types
 */
public enum UserTitle {
    MISTER ("Mr."),
    MISS ("Ms."),
    MISSES ("Mrs."),
    DOCTOR ("Dr.");

    /** the shorthand string representation of the user title  */
    private final String shortHand;


    /**
     * Constructor for the enumeration
     *
     * @param shortHand   the user's short hand
     */
    UserTitle(String shortHand) {
        this.shortHand = shortHand;
    }

    /**
     *
     * @return   the user's short hand
     */
    public String getShortHand() {
        return shortHand;
    }

    /**
     *
     * @return the display string representation
     */
    public String toString() {
        return shortHand;
    }
}
