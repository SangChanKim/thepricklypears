package fxapp;

import controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFXApplication extends Application {

    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");

    /** the main container for the application window */
    private Stage mainScreen;

    private BorderPane rootLayout;

    private User currUser;

    private List<User> authUsers;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainScreen = primaryStage;
        authUsers = new ArrayList<>();
        initRootLayout(mainScreen);
        showWelcomeScreen();
    }

    /**
     * Allows controllers to get list of all authorized users
     * @return list of the users
     */
    public List<User> getAuthUsers() {
        return authUsers;
    }

    /**
     * Allows RegistrationController to add user to pseudo-backend of authorized users
     * @param newUser the user to add
     * @return whether the new user was added or not
     */
    public boolean addAuthUser(User newUser) {
        if (authUsers.contains(newUser)) {
            return false;
        } else {
            authUsers.add(newUser);
            return true;
        }
    }

    /**
     * Allows RegistrationController to delete user so it can add the updated user
     * @param user user being updated
     * @return whether remove was successful or not
     */
    public boolean removeAuthUser(String user) {
        for (User auth : authUsers) {
            if (auth.getUsername().equals(user)) {
                authUsers.remove(auth);
                return true;
            }
        }
        return false;
    }

    /**
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param mainScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage mainScreen) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/WelcomeScreen.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);

            // Set the Main App title
            mainScreen.setTitle("Clean Water Crowd Sourcing");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }

    public void showWelcomeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/WelcomeScreen.fxml"));

            BorderPane welcomeScreen = loader.load();

            rootLayout.setCenter(welcomeScreen);

            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for WelcomeScreen!!");
            e.printStackTrace();
        }
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/LoginScreen.fxml"));

            BorderPane loginScreen = loader.load();

            rootLayout.setCenter(loginScreen);

            LoginScreenController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for LoginScreen!!");
            e.printStackTrace();
        }
    }

    public void showHomeScreen(String username) {
        try {
            for (User auth : authUsers) {
                if (auth.getUsername().equals(username)) {
                    currUser = auth;
                }
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/HomeScreen.fxml"));

            BorderPane homeScreen = loader.load();

            rootLayout.setCenter(homeScreen);

            HomeScreenController controller = loader.getController();
            controller.setUser(currUser);
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for HomeScreen!!");
            e.printStackTrace();
        }
    }

    public void showRegistrationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/RegistrationScreen.fxml"));

            BorderPane registerScreen = loader.load();

            rootLayout.setCenter(registerScreen);

            RegistrationScreenController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for RegistrationScreen!!");
            e.printStackTrace();
        }
    }

    public void showEditScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/UpdateProfileScreen.fxml"));

            BorderPane editScreen = loader.load();

            rootLayout.setCenter(editScreen);

            UpdateProfileController controller = loader.getController();
            controller.setUser(currUser);
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for UpdateProfileScreen!!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
