package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.User;

/**
 * Created by Michael Wang on 9/21/2016.
 *
 * Represents a controller for home screen
 *
 */
public class HomeScreenController {
    private MainFXApplication mainApplication;

    /*  **********************
            References to the FXML widgets in the .fxml file
        */
    @FXML
    private Label usernameLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label homeAddressLabel;

    @FXML
    private Label userTypeLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button editButton;

    @FXML
    private Button viewMapButton;

    @FXML
    private Button viewAllReportsButton;

    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * called when the user clicks Logout
     */
    @FXML
    public void onLogoutPressed() {
        mainApplication.showWelcomeScreen();
    }

    /**
     * called when the user clicks Edit
     */
    @FXML
    public void onEditPressed() {
        mainApplication.showEditScreen();
    }

    /**
     * called when the user clicks Submit Report
     */
    @FXML
    public void onViewMapPressed() {
        mainApplication.showMapScreen();
    }

    /**
     * called when the user clicks view all reports
     */
    @FXML
    public void onViewAllReportsPressed() {
        mainApplication.showAllReportsScreen();
    }

    /**
     * called when the user clicks view all quality reports
     */
    @FXML
    public void onViewAllQualityReportsPressed() {
        mainApplication.showAllQualityReportsScreen();
    }


    /**
     * passes current user value to this controller and displays all user information
     * @param currUser current user
     */
    public void setUser(User currUser) {
        usernameLabel.setText("Username: " + currUser.getUsername());
        nameLabel.setText("Name: " + currUser.getUserTitle().toString() + " " + currUser.getName());
        emailLabel.setText("Email: " + currUser.getEmailAddress());
        homeAddressLabel.setText("Address: " + currUser.getHomeAddress());
        userTypeLabel.setText("User Type: " + currUser.getUserType().toString());
    }

}
