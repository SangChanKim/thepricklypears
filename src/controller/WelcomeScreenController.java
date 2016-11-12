package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * Created by sang on 9/19/16.
 */
public class WelcomeScreenController {

    private MainFXApplication mainApplication;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    public void onRegisterPressed() {mainApplication.showRegistrationScreen();}

    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    /**
     * called when user clicks login
     */
    @FXML
    public void onLoginPressed() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (validate(username, password)) {
            // bring user to application
            mainApplication.showHomeScreen(username);
        } else {
            // bring up error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Incorrect Username/Password Combination");
            alert.setContentText("Please Try Again");

            alert.showAndWait();
        }

    }

    /**
     * checks if user input is valid
     * @param user the entered username
     * @param pass the entered password
     * @return if information is valid or not
     */
    private boolean validate(String user, String pass) {
        return mainApplication.authenticate(user, pass);
    }
}
