package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Location;
import model.User;
import model.WaterSourceReport;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Victor on 10/18/2016.
 */
public class MapController implements Initializable, MapComponentInitializedListener {

    private MainFXApplication mainApplication;

    private GoogleMap map;

    private User currUser;

    private boolean pseudoPinPlaced = false;

    private Marker pseudoMarker;

    private Location pseudoLocation;

    private String pinText;

    /*  **********************
            References to the FXML widgets in the .fxml file
        */
    @FXML
    private GoogleMapView mapView;

    @FXML
    private Button mapBackButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button addReportButton;

    @FXML
    private Button addQualityReportButton;

    @FXML
    private Label selectedPin;

    /**
     * sets main application
     * @param main main application
     */
    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        pinText = "none";
        selectedPin.setText(pinText);

        //set up the center location for the map
        LatLong center = new LatLong(34, -88);

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

        //place pins on locations
        updateMap();

        //logic and callback for placing a pseudopin
        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
                        LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
                        pseudoLocation = new Location(ll.getLatitude(), ll.getLongitude(), "");
                        pinText = "none";
                        selectedPin.setText(pinText);
                        if (pseudoPinPlaced) {
                            map.removeMarker(pseudoMarker);
                            pseudoPinPlaced = false;
                        }
                        MarkerOptions pseudoOptions = new MarkerOptions();
                        pseudoOptions.position(ll)
                                .visible(Boolean.TRUE)
                                .animation(Animation.DROP)
                                .icon(MarkerImageFactory.createMarkerImage("../greenMarker.jpg", "jpg")); //cant make green for some reason, too hard
                        pseudoPinPlaced = true;
                        pseudoMarker = new Marker(pseudoOptions);
                        map.addMarker(pseudoMarker);
        });
    }

    /**
     * called when the user clicks back and brings back to home screen
     */
    @FXML
    private void onBackPressed() {
        mainApplication.showHomeScreen(currUser.getUsername());
    }

    /**
     * called when add report button is pressed
     */
    @FXML
    private void onAddReportPressed() {
        if (pseudoPinPlaced) {
            mainApplication.showCreateWaterReportScreen(pseudoLocation);
        } else {
            mainApplication.showCreateWaterReportScreen(null);
        }
    }

    /**
     * called when add quality report button is pressed
     */
    @FXML
    private void onAddQualityReportPressed() {
            mainApplication.showCreateWaterQualityReportScreen(pseudoLocation);
    }


    /**
     * called when clear button is pressed
     */
    @FXML
    private void onClearPressed() {
        if (pseudoPinPlaced) {
            map.removeMarker(pseudoMarker);
            pseudoPinPlaced = false;
        }
    }

    /**
     * passes current user value to this controller
     * @param auth current user
     */
    public void setUser(User auth) {
        currUser = auth;
    }

    /**
     * Gets all of the water source reports and puts pins at their locations.
     */
    private void updateMap() {
        List<WaterSourceReport> waterSourceReports = mainApplication.getWaterSourceReports();
        for(WaterSourceReport report : waterSourceReports) {
            MarkerOptions markerOptions = new MarkerOptions();
            Location l = report.getLocation();
            LatLong loc = new LatLong(l.getLatitude(), l.getLongitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(l.getTitle());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
                LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
                pseudoLocation = new Location(ll.getLatitude(), ll.getLongitude(), l.getTitle());
                pinText = l.getTitle();
                selectedPin.setText(pinText);
                InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content("" + "<h2>" + l.getTitle() + "</h2> <br>Water Condition: " + report.getWaterCondition() + "<br>Water Type: " + report.getWaterType());

                InfoWindow window = new InfoWindow(infoWindowOptions);
                window.open(map, marker);
            });
            map.addMarker(marker);
        }


    }
}
