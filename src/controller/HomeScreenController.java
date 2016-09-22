package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Michael Wang on 9/21/2016.
 */
public class HomeScreenController {
    private MainFXApplication mainApplication;

    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    @FXML
    private Button logoutButton;

    @FXML
    public void onLogoutPressed() {
        mainApplication.showWelcomeScreen();
    }

}
