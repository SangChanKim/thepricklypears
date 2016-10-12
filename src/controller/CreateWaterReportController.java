package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

import java.util.Date;

/**
 * Created by Valerie on 10/10/2016.
 */
public class CreateWaterReportController {

    private MainFXApplication mainApplication;

    private Date date = new Date();

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
    private ComboBox<WaterType> waterTypeComboBox;

    @FXML
    private ComboBox<WaterCondition> conditionComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createButton;

    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * called when the user clicks Logout
     */
    @FXML
    public void onCreatePressed() {
        WaterSourceReport report = new WaterSourceReport(currUser.getUsername
                (), "6", date, locationTextField.getText(),
                waterTypeComboBox.getValue(), conditionComboBox.getValue());
        mainApplication.showHomeScreen(currUser.getUsername());
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

        //TODO: set up report number

        dateLabel.setText(date.toString());
    }

    /**
     * called when the user clicks Cancel
     */
    @FXML
    public void onCancelPressed() {
        mainApplication.showHomeScreen(currUser.getUsername());
    }

    /**
     * passes current user value to this controller
     * @param auth current user
     */
    public void setUser(User auth) {
        currUser = auth;
        usernameLabel.setText(currUser.getUsername());
    }

}
