package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.User;
import model.UserTitle;
import model.UserType;

/**
 * Created by sang on 9/19/16.
 */
public class RegistrationScreenController {

    private MainFXApplication mainApplication;


    /*  **********************
        References to the FXML widgets in the .fxml file
    */
    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox userTypeComboBox;

    @FXML
    private ComboBox titleComboBox;

    @FXML
    private TextField emailAddressTextField;

    @FXML
    private TextField homeAddressTextField;

    @FXML
    private TextField phoneNumberTextField;

    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }


    // TODO IMPLEMENT USER TYPE AND TITLE
//    /**
//     * called automatically after load
//     */
//    @FXML
//    private void initialize() {
//        UserType[] types = UserType.values();
//        userTypeComboBox.getItems().addAll(types);
//        userTypeComboBox.setValue(types[0]);
//
//        UserTitle[] titles = UserTitle.values();
//        titleComboBox.getItems().addAll(titles);
//        titleComboBox.setValue(types[0]);
//    }


    /**
     * Called when the user clicks Cancel.
     */
    @FXML
    public void onCancelPressed() {
        mainApplication.showWelcomeScreen();
    }


    /**
     * Called when the user clicks Register.
     */
    @FXML
    public void onRegisterPressed() {
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        // Confirm their password
        if (confirmPassword.equals(password)) {

            // Get all their attributes
            String username = usernameTextField.getText();
            String name = nameTextField.getText();
            String emailAddress = emailAddressTextField.getText();
            String homeAddress = homeAddressTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();

            // TODO IMPLEMENT USER TYPE AND TITLE
            //UserType type = UserType.valueOf((String)userTypeComboBox.getSelectionModel().getSelectedItem());
            //UserTitle title = UserTitle.valueOf((String)titleComboBox.getSelectionModel().getSelectedItem());

            // Set the attributes
            User newUser = new User(username, password);
            newUser.setUsername(username);
            newUser.setName(name);
            newUser.setEmailAddress(emailAddress);
            newUser.setHomeAddress(homeAddress);
            newUser.setPhoneNumber(phoneNumber);

            // TODO IMPLEMENT USER TYPE AND TITLE
            //newUser.setUserType(type);
            //newUser.setUserTitle(title);

            mainApplication.showHomeScreen();

        } else {
            // Show on the UI that passwords are not equal
            // TODO

            // Clear the password/confirm password text fields
            passwordTextField.clear();
            confirmPasswordTextField.clear();
        }
    }

}
