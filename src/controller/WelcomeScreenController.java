package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * Created by sang on 9/19/16.
 *
 * Represents a controller for the welcome screen
 */
public class WelcomeScreenController {

    private MainFXApplication mainApplication;

    @FXML
    private Button enterLoginButton;

    @FXML
    public void onLoginPressed() {
        mainApplication.showLoginScreen();
    }

    @FXML
    public void onRegisterPressed() {mainApplication.showRegistrationScreen();}

    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }
}
