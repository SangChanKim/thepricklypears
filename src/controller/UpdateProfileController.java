package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import model.UserTitle;
import model.UserType;

/**
 * Created by Victor on 10/2/2016.
 */
public class UpdateProfileController {

    private MainFXApplication mainApplication;
    private User currUser;


    /*  **********************
        References to the FXML widgets in the .fxml file
    */
    @FXML
    private Label currUserLabel;

    @FXML
    private Button updateButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<UserType> userTypeComboBox;

    @FXML
    private ComboBox<UserTitle> titleComboBox;

    @FXML
    private TextField emailAddressTextField;

    @FXML
    private TextField homeAddressTextField;

    @FXML
    private TextField phoneNumberTextField;

    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * passes the current user value to controller
     * @param auth the current user
     */
    public void setUser(User auth) {
        this.currUser = auth;
        //load in previous information
        nameTextField.setText(currUser.getName());
        emailAddressTextField.setText(currUser.getEmailAddress());
        homeAddressTextField.setText(currUser.getHomeAddress());
        phoneNumberTextField.setText(currUser.getPhoneNumber());
        userTypeComboBox.setValue(currUser.getUserType());
        titleComboBox.setValue(currUser.getUserTitle());
        currUserLabel.setText(currUser.getUsername());
    }


    /**
     * called automatically after load
     */
    @FXML
    private void initialize() {
        //populate comboboxes
        UserType[] types = UserType.values();
        userTypeComboBox.getItems().addAll(types);
        userTypeComboBox.setValue(types[0]);

        UserTitle[] titles = UserTitle.values();
        titleComboBox.getItems().addAll(titles);
        titleComboBox.setValue(titles[0]);
    }

    /**
     * Called when the user clicks Cancel.
     */
    @FXML
    public void onCancelPressed() {
        mainApplication.showHomeScreen(currUser.getUsername());
    }

    /**
     * Called when the user clicks Update
     */
    @FXML
    public void onEditPressed() {
            // Get all their attributes
            String name = nameTextField.getText();
            String emailAddress = emailAddressTextField.getText();
            String homeAddress = homeAddressTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();

            UserType type = userTypeComboBox.getSelectionModel().getSelectedItem();
            UserTitle title = titleComboBox.getSelectionModel().getSelectedItem();

            // Set the attributes
            currUser.setName(name);
            currUser.setEmailAddress(emailAddress);
            currUser.setHomeAddress(homeAddress);
            currUser.setPhoneNumber(phoneNumber);

            currUser.setUserType(type);
            currUser.setUserTitle(title);

            //update user by deleted from list and adding back in
            mainApplication.removeAuthUser(currUser.getUsername());
            mainApplication.addAuthUser(currUser);
            mainApplication.showHomeScreen(currUser.getUsername());
    }



}
