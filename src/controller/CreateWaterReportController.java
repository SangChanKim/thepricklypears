package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import model.User;
import model.Location;
import model.WaterType;
import model.WaterCondition;
import model.WaterSourceReport;


import java.util.Date;


/**
 * Created by Valerie on 10/10/2016.
 *
 * Represents a controller for water source report creation
 */
public class CreateWaterReportController {

    private MainFXApplication mainApplication;

    private final Date date = new Date();

    private int reportNumber;

    private User currUser;

    private static final double LAT_LOWER_BOUND = -90.0;

    private static final double LAT_UPPER_BOUND = 90.0;

    private static final double LONG_LOWER_BOUND = -180.0;

    private static final double LONG_UPPER_BOUND = 180.0;


    /*  **********************
            References to the FXML widgets in the .fxml file
        */
    @FXML
    private Label usernameLabel;

    @FXML
    private Label reportNumberLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField locationTextField;

    @FXML
    private ComboBox<WaterType> waterTypeComboBox;

    @FXML
    private ComboBox<WaterCondition> conditionComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField latField;

    @FXML
    private TextField longField;

    /**
     * sets main application
     * @param main main application
     */
    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * called when the user clicks Create
     */
    @FXML
    public void onCreatePressed() {
        if ((Double.parseDouble(latField.getText()) >= LAT_LOWER_BOUND)
                && (Double.parseDouble(latField.getText()) <= LAT_UPPER_BOUND)
                && (Double.parseDouble(longField.getText()) >= LONG_LOWER_BOUND)
                && (Double.parseDouble(longField.getText())<= LONG_UPPER_BOUND)) {
            mainApplication.addWaterSourceReport(new WaterSourceReport(
                    currUser.getUsername(),
                    reportNumber,
                    date,
                    new Location(
                            Double.parseDouble(latField.getText()),
                            Double.parseDouble(longField.getText()),
                            locationTextField.getText()),
                    waterTypeComboBox.getValue(),
                    conditionComboBox.getValue()));

            mainApplication.showMapScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Create Water Source Report Failed");
            alert.setHeaderText("Incorrect Latitude/Longitude Combination");
            alert.setContentText("Please Try Again");

            alert.showAndWait();

        }
    }

    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
        WaterType[] types = WaterType.values();
        waterTypeComboBox.getItems().addAll(types);
        waterTypeComboBox.setValue(types[0]);

        WaterCondition[] conditions = WaterCondition.values();
        conditionComboBox.getItems().addAll(conditions);
        conditionComboBox.setValue(conditions[0]);

        reportNumberLabel.setText("" + reportNumber);

        dateLabel.setText(date.toString());
    }

    /**
     * called when the user clicks Cancel
     */
    @FXML
    public void onCancelPressed() {
        mainApplication.showMapScreen();
    }

    /**
     * passes current user value to this controller
     * @param auth current user
     */
    public void setUser(User auth) {
        currUser = auth;
        usernameLabel.setText(currUser.getUsername());
    }

    /**
     * sets the report number
     * @param num report number
     */
    public void setReportNumber(int num) {
        reportNumber = num;
        reportNumberLabel.setText("" + reportNumber);
    }

    /**
     * sets the pseudo location
     * @param pseudo the pseudo location
     */
    public void setPseudoLocation(Location pseudo) {
        if (pseudo != null) {
            latField.setText("" + pseudo.getLatitude());
            longField.setText("" + pseudo.getLongitude());
        }
    }

}
