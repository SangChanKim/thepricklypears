package fxapp;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFXApplication extends Application {

    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");

    /** the main container for the application window */
    private Stage mainScreen;

    private BorderPane rootLayout;

    private User currUser;

    private List<User> authUsers;

    private List<WaterSourceReport> waterSourceReports;

    private List<WaterQualityReport> waterQualityReports;

    private Firebase db;

    private int currWaterSourceReportNum;
    private boolean passwordIsValid;


    @Override
    public void start(Stage primaryStage) throws Exception {

        this.db = new Firebase("https://thepricklypears-9a5e4.firebaseio.com/");

        mainScreen = primaryStage;
        authUsers = new ArrayList<>();
        // For Testing

        waterSourceReports = new ArrayList<>();


        db.child("waterSourceReports").child("maxReportNum").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //System.out.println(dataSnapshot);
                long max = (long) dataSnapshot.getValue();
                currWaterSourceReportNum = (int) max;
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        db.child("waterSourceReports").child("reports").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Object> listOfReports = (HashMap<String, Object>)dataSnapshot.getValue();
                for (String key: listOfReports.keySet()) {
                    HashMap<String, String> map = (HashMap<String, String>) listOfReports.get(key);
                    String user = map.get("user");
                    int reportNumber = Integer.parseInt(map.get("reportNumber"));
                    double lat = Double.parseDouble(map.get("lat"));
                    double longg = Double.parseDouble(map.get("long"));
                    String locationTitle = map.get("locationTitle");

                    String waterTypeStr = map.get("waterType");
                    WaterType[] types = WaterType.values();
                    WaterType realType = types[0];
                    for (WaterType type: types) {
                        if (type.getType().equals(waterTypeStr)) {
                            realType = type;
                        }
                    }

                    String waterConditionStr = map.get("waterType");
                    WaterCondition[] conditions = WaterCondition.values();
                    WaterCondition realCondition = conditions[0];
                    for (WaterCondition condition: conditions) {
                        if (condition.getCondition().equals(waterConditionStr)) {
                            realCondition = condition;
                        }
                    }

                    long timestamp = Long.parseLong(map.get("dateCreated"));
                    Date date = new Date(timestamp);

                    WaterSourceReport report = new WaterSourceReport(user,
                            reportNumber,
                            date,
                            new Location(lat, longg, locationTitle),
                            realType,
                            realCondition);
                    waterSourceReports.add(report);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        waterQualityReports = new ArrayList<>();
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
     * Allows RegistrationController to add user to pseudo-backen und of authorized users
     * @param newUser the user to add
     * @return whether the new user was added or not
     */
    public boolean addAuthUser(User newUser) {
        Firebase usersRef = db.child("users");

        Map<String, String> userDataMap = new HashMap<String, String>();
        userDataMap.put("name", newUser.getName());
        userDataMap.put("username", newUser.getUsername());
        userDataMap.put("password", newUser.getPassword());
        userDataMap.put("emailAddress", newUser.getEmailAddress());
        userDataMap.put("homeAddress", newUser.getHomeAddress());
        userDataMap.put("phoneNumber", newUser.getPhoneNumber());
        userDataMap.put("userType", newUser.getUserType().toString());
        userDataMap.put("userTitle", newUser.getUserTitle().toString());

        Map<String, Map<String, String>> users = new HashMap<>();
        users.put(newUser.getUsername(), userDataMap);

        usersRef.setValue(users);

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
                db.child("users").child(auth.getUsername()).removeValue();
                authUsers.remove(auth);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks database and authenticates user accordingly
     *
     * @param user
     * @param pass
     * @return
     */
    public boolean authenticate(String user, String pass) {
        passwordIsValid = false;
        if (db.child("users").child(user) == null) {
            System.out.println("User not in system");
            return passwordIsValid;
        } else {
            Firebase ref = db.child("users").child(user).child("password");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    // do some stuff once
                    if (snapshot == null || snapshot.getValue() == null) {
                        System.out.println("User is not in the system");
                        passwordIsValid = false;
                    } else if (snapshot.getValue().toString().equals(pass)) {
                        passwordIsValid = true;
                        System.out.println("User is authenticated");
                    } else {
                        System.out.println("Incorrect password");
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done authenticating");
        System.out.println(passwordIsValid);
        return passwordIsValid;
    }

    /**
     * Allows controllers to get list of all water source reports
     * @return list of the water source reports
     */
    public List<WaterSourceReport> getWaterSourceReports() {
        return waterSourceReports;
    }

    /**
     * Allows CreateWaterReportController to add report to pseudo-backend of reports
     * @param newReport the report to add
     * @return whether the new report was added or not
     */
    public boolean addWaterSourceReport(WaterSourceReport newReport) {
        if (waterSourceReports.contains(newReport)) {
            return false;
        } else {
            waterSourceReports.add(newReport);
            Map<String, String> reportDataMap = new HashMap<String, String>();
            reportDataMap.put("reportNumber", newReport.getReportNumber().toString());
            reportDataMap.put("waterCondition", newReport.getWaterCondition().toString());
            reportDataMap.put("user", newReport.getUsername());
            reportDataMap.put("dateCreated", "" + newReport.getDate().getTime());
            reportDataMap.put("waterType", newReport.getWaterType().toString());
            reportDataMap.put("locationTitle", newReport.getLocation().getTitle());
            reportDataMap.put("lat", "" + newReport.getLocation().getLatitude());
            reportDataMap.put("long","" + newReport.getLocation().getLongitude());
            db.child("waterSourceReports").child("reports").push().setValue(reportDataMap);
            currWaterSourceReportNum++;
            db.child("waterSourceReports").child("maxReportNum").setValue(currWaterSourceReportNum);

            LOGGER.log(Level.INFO, "Persisting " + newReport.toString() + " to Firebase");
            return true;
        }
    }

    /**
     * Allows CreateWaterQualityReportController to add report to pseudo-backend of reports
     * @param newQualityReport the report to add
     * @return whether the new quality report was added or not
     */
    public boolean addWaterQualityReport(WaterQualityReport newQualityReport) {
        if (waterQualityReports.contains(newQualityReport)) {
            return false;
        } else {
            waterQualityReports.add(newQualityReport);
            return true;
        }
    }

    /**
     * Allows ViewAllReportsController to delete report
     * @param reportNumber report being updated
     * @return whether remove was successful or not
     */
    public boolean removeWaterSourceReport(int reportNumber) {
        for (WaterSourceReport report: waterSourceReports) {
            if (report.getReportNumber().equals(reportNumber)) {
                waterSourceReports.remove(report);
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

    public void showCreateWaterReportScreen(Location loc) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/CreateWaterReportScreen.fxml"));

            BorderPane CreateWaterReportScreen = loader.load();

            rootLayout.setCenter(CreateWaterReportScreen);

            CreateWaterReportController controller = loader.getController();
            controller.setUser(currUser);
            controller.setReportNumber(currWaterSourceReportNum + 1);

            controller.setPseudoLocation(loc);
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for CreateWaterReportScreen!!");
            e.printStackTrace();
        }
    }

    public void showCreateWaterQualityReportScreen(Location loc) {
        UserType type = currUser.getUserType();
        if (type.equals(UserType.MANAGER) || type.equals(UserType.WORKER)) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainFXApplication.class.getResource("../view/CreateWaterQualityReportScreen.fxml"));

                BorderPane CreateWaterQualityReportScreen = loader.load();

                rootLayout.setCenter(CreateWaterQualityReportScreen);

                CreateWaterQualityReportController controller = loader.getController();
                controller.setUser(currUser);
                controller.setReportNumber(waterQualityReports.size() + 1);
                controller.setPseudoLocation(loc);
                controller.setMainApp(this);

            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to find the fxml file for CreateWaterQualityReportScreen!!");
                e.printStackTrace();
            }
        } else {
            LOGGER.log(Level.WARNING, "A unprivileged user tried to access the quality report screen");
        }
    }

    public void showAllReportsScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/ViewAllReportsScreen.fxml"));

            BorderPane ViewAllReportsScreen = loader.load();

            rootLayout.setCenter(ViewAllReportsScreen);

            ViewAllReportsController controller = loader.getController();
            controller.setUser(currUser);
            controller.setReports(waterSourceReports);
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for ViewAllReportsScreen!!");
            e.printStackTrace();
        }
    }

    public void showAllQualityReportsScreen() {
        UserType type = currUser.getUserType();
        if (type.equals(UserType.MANAGER)) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainFXApplication.class.getResource("../view/ViewAllQualityReportsScreen.fxml"));

                BorderPane viewAllQualityReportsScreen = loader.load();

                rootLayout.setCenter(viewAllQualityReportsScreen);

                ViewAllQualityReportsController controller = loader.getController();
                controller.setUser(currUser);
                controller.setReports(waterQualityReports);
                controller.setMainApp(this);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to find the fxml file for ViewAllQualityReportsScreen!!");
                e.printStackTrace();
            }
        }
    }

    public void showMapScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/mapview.fxml"));

            BorderPane MapScreen = loader.load();

            rootLayout.setCenter(MapScreen);

            MapController controller = loader.getController();
            controller.setUser(currUser);
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MapScreen!!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
