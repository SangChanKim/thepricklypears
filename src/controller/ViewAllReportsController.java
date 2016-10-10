package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;

/**
 * Created by Valerie on 10/10/2016.
 */
public class ViewAllReportsController {

    private MainFXApplication mainApplication;

    private User currUser;

    /*  **********************
            References to the FXML widgets in the .fxml file
        */
    @FXML
    private Button homeButton;





    /**
     * called when the user clicks Home
     */
    @FXML
    public void onHomePressed() {
        mainApplication.showHomeScreen(currUser.getUsername());
    }

    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * passes current user value to this controller
     * @param auth current user
     */
    public void setUser(User auth) {
        currUser = auth;
        //usernameLabel.setText(currUser.getUsername());
    }
}
