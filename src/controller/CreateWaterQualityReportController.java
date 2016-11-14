package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Location;
import model.QualityCondition;
import model.User;
import model.WaterQualityReport;
import java.util.Date;


/**
 * Created by Sang on 10/24/16.
 */
public class CreateWaterQualityReportController {

    private MainFXApplication mainApplication;

    private Date date = new Date();

    private int reportNumber;

    private User currUser;

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
    private ComboBox<QualityCondition> conditionComboBox;

    @FXML
    private TextField virusPPMTextField;

    @FXML
    private TextField contaminantPPMTextField;

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
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }


    /**
     * called when the user clicks Logout
     */
    @FXML
    public void onCreatePressed() {
        WaterQualityReport report = new WaterQualityReport(
                currUser.getUsername(),
                reportNumber,
                date,
                new Location(
                        Double.parseDouble(latField.getText()),
                        Double.parseDouble(longField.getText()),
                        locationTextField.getText().toString()),
                conditionComboBox.getValue(),
                Integer.parseInt(virusPPMTextField.getText()),
                Integer.parseInt(contaminantPPMTextField.getText())
                );
        mainApplication.addWaterQualityReport(report);
        mainApplication.showMapScreen();
    }

    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
        QualityCondition[] conditions = QualityCondition.values();
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
     * @param pseudo the water source location
     */
    public void setPseudoLocation(Location pseudo) {
        Location pseudoLocation = pseudo;
        if (pseudoLocation != null) {
            latField.setText("" + pseudoLocation.getLatitude());
            longField.setText("" + pseudoLocation.getLongitude());
            locationTextField.setText("" + pseudoLocation.getTitle());
        }
    }

}
