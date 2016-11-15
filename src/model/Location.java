package model;


/**
 * Created by Victor on 10/18/2016.
 */
public class Location {

    private double latitude;
    private double longitude;
    private String title;

    /**
     * creates a location
     * @param latitude latitude coord
     * @param longitude longitude coord
     * @param title name of location
     */
    public Location(double latitude, double longitude, String title) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title;
    }

    /**
     * checks if longitude/latitude and location title are the same for two Locations
     * @param l2 other Location to compare with
     * @return whether they are equal or not
     */
    public boolean equals(Location l2) {
        if (l2 != null && (this.getLatitude() == l2.getLatitude() && this.getLongitude() == l2.getLongitude()) && (this.getTitle().equals(l2.getTitle()))) {
            return true;
        }
        return false;
    }

}
