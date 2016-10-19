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

}
