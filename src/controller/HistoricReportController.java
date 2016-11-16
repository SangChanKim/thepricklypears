package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.util.StringConverter;
import model.DateAxis310;
import model.Location;
import model.WaterQualityReport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Created by Valerie on 11/8/2016.
 *
 * Represents a controller for historic report creation
 */
public class HistoricReportController {

    private MainFXApplication mainApplication;

    private List<WaterQualityReport> reports;

    final DateAxis310 xAxis = new DateAxis310();
    final NumberAxis yAxis = new NumberAxis();


    /*  **********************
            References to the FXML widgets in the .fxml file
        */
    @FXML
    private Label yearAndLocationLabel;

    @FXML
    private LineChart<LocalDateTime, Number> waterQualityHistoryGraph;

    @FXML
    private Button backToMapButton;


    /**
     * sets main application
     * @param main main application
     */
    public void setMainApp (MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * set up date range and list of reports to display
     * @param startDate the start date of our trend
     * @param endDate the end date of our trend
     * @param reports the number of reports we want to generate on
     */
    public void setDateAndData(Date startDate, Date endDate, List<WaterQualityReport> reports) {
        this.reports = reports;
        Location loc = reports.get(0).getLocation();
        yearAndLocationLabel.setText(loc.getTitle() + " from " + startDate + " to " + endDate);
    }

    /**
     * display graph on-screen
     */
    public void setUpGraph() {

        final StringConverter<LocalDateTime> STRING_CONVERTER = new StringConverter<LocalDateTime>() {
            @Override public String toString(LocalDateTime localDateTime) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy\nHH:mm:ss");
                return dtf.format(localDateTime);
            }
            @Override public LocalDateTime fromString(String s) {
                return LocalDateTime.parse(s);
            }
        };
        //defining the axes
        final DateAxis310 xAxis = new DateAxis310();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        xAxis.setTickLabelFormatter(STRING_CONVERTER);
        yAxis.setLabel("PPM");
        //creating the chart

        //defining a series
        XYChart.Series<LocalDateTime, Number> series = new XYChart.Series();
        series.setName("Contaminant PPM");
        XYChart.Series<LocalDateTime, Number> series2 = new XYChart.Series();
        series2.setName("Virus PPM");
        //populating the series with data
        this.reports.sort(null);
        for (WaterQualityReport report: this.reports) {
            series.getData().add(new XYChart.Data(report.getDate().toString(), report.getContaminantPPM()));
            series2.getData().add(new XYChart.Data(report.getDate().toString(), report.getVirusPPM()));
//            series.getData().add(new XYChart.Data(report.getDate(), report.getContaminantPPM()));
        }

        xAxis.setTickLabelFormatter(STRING_CONVERTER);
        waterQualityHistoryGraph.getData().addAll(series, series2);
//        waterQualityHistoryGraph.setData(series);

    }


    /**
     * called when the user clicks Back to Map
     */
    @FXML
    public void onBackPressed() {
        mainApplication.showMapScreen();
    }


}
