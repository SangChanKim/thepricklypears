package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginScreenController {

    private MainFXApplication mainApplication;

    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    @FXML
    private Button cancelButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    public void onCancelPressed() {
            mainApplication.showWelcomeScreen();
    }


    @FXML
    public void onLoginPressed() {
        String username = "prickly";
        String password = "pear";

        if (usernameTextField.getText().equals(username) && passwordTextField.getText().equals(password)) {
            // bring user to application
            mainApplication.showHomeScreen();
        } else {
            // bring up error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Incorrect Username/Password Combination");
            alert.setContentText("Please Try Again");

            alert.showAndWait();
        }
    }

}
