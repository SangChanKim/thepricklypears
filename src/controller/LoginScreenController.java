package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;

public class LoginScreenController {

    private MainFXApplication mainApplication;

    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    /*  **********************
            References to the FXML widgets in the .fxml file
        */
    @FXML
    private Button cancelButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    /**
     * called when user clicks Cancel
     */
    @FXML
    public void onCancelPressed() {
            mainApplication.showWelcomeScreen();
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
        boolean valid = false;
        for (User auth : mainApplication.getAuthUsers()) {
            if (auth.getUsername().equals(user) && auth.getPassword().equals(pass)) {
                valid = true;
            }
        }
        return valid;
    }

}
