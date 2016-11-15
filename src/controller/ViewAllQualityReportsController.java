package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by sang on 10/26/16.
 *
 * Represents a controller for view all quality
 */
public class ViewAllQualityReportsController {
    private MainFXApplication mainApplication;

    private User currUser;

    @FXML
    private Button homeButton;

    @FXML
    private TableView<WaterQualityReport> reportsTableView;

    @FXML
    private TableColumn reportNumCol;

    @FXML
    private TableColumn dateCol;

    @FXML
    private TableColumn locationCol;

    @FXML
    private TableColumn virusPPMCol;

    @FXML
    private TableColumn contaminantPPMCol;

    @FXML
    private TableColumn conditionCol;

    @FXML
    private TableColumn userCol;


    /**
     * Puts all reports into the table
     * @param reports list of all water source reports in the system
     */
    public void setReports(List<WaterQualityReport> reports) {
        ObservableList<WaterQualityReport> obsReports = FXCollections.observableArrayList(reports);

        reportNumCol.setCellValueFactory(
                new PropertyValueFactory<WaterQualityReport,Integer>("reportNumber")
        );

        dateCol.setCellValueFactory(
                new PropertyValueFactory<WaterQualityReport,Date>("date")
        );

        locationCol.setCellValueFactory(
                new PropertyValueFactory<WaterQualityReport, Location>("location")
        );

        conditionCol.setCellValueFactory(
                new PropertyValueFactory<WaterQualityReport, QualityCondition>("qualityCondition")
        );

        virusPPMCol.setCellValueFactory(
                new PropertyValueFactory<WaterQualityReport,Integer>("virusPPM")
        );

        contaminantPPMCol.setCellValueFactory(
                new PropertyValueFactory<WaterQualityReport,Integer>("contaminantPPM")
        );

        userCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport,String>("username")
        );

        reportsTableView.setItems(obsReports);

    }


    /**
     * Called when the user clicks Home
     */
    @FXML
    public void onHomePressed() {
        System.out.println(mainApplication == null);
        mainApplication.showHomeScreen(currUser.getUsername());
    }

    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Passes current user value to this controller
     * @param auth current user
     */
    public void setUser(User auth) {
        currUser = auth;
    }
}
