package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.User;
import sun.rmi.runtime.Log;

/**
 * Created by Michael Wang on 9/21/2016.
 */
public class HomeScreenController {
    private MainFXApplication mainApplication;

    private User currUser;

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
    private Label userTitleLabel;

    @FXML
    private Label userTypeLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button editButton;

    @FXML
    private Button submitReportButton;

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
    public void onSubmitReportPressed() {
        mainApplication.showCreateWaterReportScreen();
    }

    /**
     * called when the user clicks Submit Report
     */
    @FXML
    public void onViewAllReportsPressed() {
        mainApplication.showAllReportsScreen();
    }

    /**
     * passes current user value to this controller
     * @param auth current user
     */
    public void setUser(User auth) {
        currUser = auth;
        usernameLabel.setText(currUser.getUsername());
        nameLabel.setText(currUser.getName());
        emailLabel.setText(currUser.getEmailAddress());
        homeAddressLabel.setText(currUser.getHomeAddress());
        userTitleLabel.setText(currUser.getUserTitle().toString());
        userTypeLabel.setText(currUser.getUserType().toString());
    }

}
