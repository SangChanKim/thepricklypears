package model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Created by Valerie on 10/11/2016.
 */
public class WaterSourceReport {

    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty reportNumber = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> date = new
            SimpleObjectProperty<Date>();
    private final StringProperty location = new SimpleStringProperty();
    private final SimpleObjectProperty<WaterType> waterType = new
            SimpleObjectProperty<WaterType>();
    private final SimpleObjectProperty<WaterCondition> waterCondition = new
            SimpleObjectProperty<WaterCondition>();

    /**
     * Make a new water source report
     * @param username      the user's name
     * @param reportNumber  the report number for this report
     * @param date          date/time of water report
     * @param location      location of water source
     * @param waterType     type of water source
     * @param waterCondition    condition of water
     */
    public WaterSourceReport(String username, String reportNumber, Date date,
     String location, WaterType waterType, WaterCondition waterCondition) {
        this.username.set(username);
        this.reportNumber.set(reportNumber);
        this.date.set(date);
        this.location.set(location);
        this.waterType.set(waterType);
        this.waterCondition.set(waterCondition);
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

    public String getReportNumber() {
        return reportNumber.get();
    }

    public StringProperty reportNumberProperty() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
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

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
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


}
