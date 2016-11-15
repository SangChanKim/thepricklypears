package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Valerie on 10/10/2016.
 */
public class ViewAllReportsController {

    private MainFXApplication mainApplication;

    private User currUser;

    /*  **********************
            References to the FXML widgets in the .fxml file
        */
    @FXML
    private Button homeButton;

    @FXML
    private TableView<WaterSourceReport> reportsTableView;

    @FXML
    private TableColumn reportNumCol;

    @FXML
    private TableColumn dateCol;

    @FXML
    private TableColumn locationCol;

    @FXML
    private TableColumn typeCol;

    @FXML
    private TableColumn conditionCol;

    @FXML
    private TableColumn userCol;


    /**
     * puts all reports into the table
     * @param reports list of all water source reports in the system
     */
    public void setReports(List<WaterSourceReport> reports) {
        List<WaterSourceReport> reports1 = reports;
        ObservableList<WaterSourceReport> obsReports = FXCollections.observableArrayList(reports);

        reportNumCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport,Integer>("reportNumber")
        );

        dateCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport,Date>("date")
        );

        locationCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, Location>("location")
        );

        typeCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, WaterType>("waterType")
        );

        conditionCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, WaterCondition>("waterCondition")
        );

        userCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport,String>("username")
        );

        reportsTableView.setItems(obsReports);

    }





    /**
     * called when the user clicks Home
     */
    @FXML
    public void onHomePressed() {
        mainApplication.showHomeScreen(currUser.getUsername());
    }

    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * passes current user value to this controller
     * @param auth current user
     */
    public void setUser(User auth) {
        currUser = auth;
        //usernameLabel.setText(currUser.getUsername());
    }
}
