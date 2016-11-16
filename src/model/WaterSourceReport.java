package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Created by Valerie on 10/11/2016.
 *
 * Represents a water source report
 */
public class WaterSourceReport {

    private final StringProperty username = new SimpleStringProperty();
    private final SimpleObjectProperty<Integer> reportNumber = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Date> date = new
            SimpleObjectProperty<>();
    private final ObjectProperty<Location> location = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<WaterType> waterType = new
            SimpleObjectProperty<>();
    private final SimpleObjectProperty<WaterCondition> waterCondition = new
            SimpleObjectProperty<>();

    /**
     * Make a new water source report
     * @param username      the user's name
     * @param reportNumber  the report number for this report
     * @param date          date/time of water report
     * @param location      location of water source
     * @param waterType     type of water source
     * @param waterCondition    condition of water
     */
    public WaterSourceReport(String username, int reportNumber, Date date,
     Location location, WaterType waterType, WaterCondition waterCondition) {
        this.username.set(username);
        this.reportNumber.set(reportNumber);
        this.date.set(date);
        this.location.set(location);
        this.waterType.set(waterType);
        this.waterCondition.set(waterCondition);
    }

    public String toString() {
        return getReportNumber() + "\t" +  getDate().toString() + "\t" + getLocation() + "\t" + waterType.toString() + "\t" + waterCondition.toString() + "\t" + username;
    }


    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public Integer getReportNumber() {
        return reportNumber.get();
    }

    public SimpleObjectProperty<Integer> reportNumberProperty() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber.set(reportNumber);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> date() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public Location getLocation() {
        return location.get();
    }

    public ObjectProperty<Location> locationProperty() {
        return location;
    }

    public void setLocation(Location location) {
        this.location.set(location);
    }

    public WaterType getWaterType() {
        return waterType.get();
    }

    public SimpleObjectProperty<WaterType> waterTypeProperty() {
        return waterType;
    }

    public void setWaterType(WaterType waterType) {
        this.waterType.set(waterType);
    }

    public WaterCondition getWaterCondition() {
        return waterCondition.get();
    }

    public SimpleObjectProperty<WaterCondition> waterConditionProperty() {
        return waterCondition;
    }

    public void setWaterCondition(WaterCondition waterCondition) {
        this.waterCondition.set(waterCondition);
    }

    @Override
    public boolean equals(Object o) {
        WaterSourceReport other = (WaterSourceReport) o;
        if (other != null) {
            return (this.getUsername().equals(other.getUsername()))
                    && (this.getReportNumber().equals(other.getReportNumber()))
                    && (this.getDate().equals(other.getDate()))
                    && (this.getLocation().equals(other.getLocation()))
                    && (this.getWaterType().equals(other.getWaterType()))
                    && (this.getWaterCondition().equals(other.getWaterCondition()));
        } else {
            return false;
        }
    }


}
