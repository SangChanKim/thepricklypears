package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;

/**
 * Created by Valerie on 10/10/2016.
 */
public class CreateWaterReportController {

    private MainFXApplication mainApplication;

    private User currUser;

    /*  **********************
            References to the FXML widgets in the .fxml file
        */
    @FXML
    private Label usernameLabel;

    @FXML
    private Label reportNumberLabel;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField locationTextField;

//    @FXML
//    private ComboBox<WaterType> waterTypeComboBox;
//
//    @FXML
//    private ComboBox<WaterCondition> conditionComboBox;

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
        //TODO: submit report
    }

    /**
     * called when the user clicks Edit
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
