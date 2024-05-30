package com.example.newplan.UIController;

import com.example.newplan.HelloApplication;
import com.example.newplan.model.AppTrackerDAO;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// This is an example controller utilizing the settings page, to change pages you need to edit the HelloApplication Class
public class reportController implements Controller {

    // Label the buttons you need, below are the buttons for the nav, They shouldn't be included in the login or signup pages

    @FXML
    private Button settings;
    @FXML
    private Button home;
    @FXML
    private Button reminders;
    @FXML
    private Button restrictions;
    @FXML
    private Button information;
    @FXML
    private Button report;
    @FXML
    private Button logout;

    @FXML
    private BarChart<String, Number> dailyBarChart;

    @FXML
    private BarChart<String, Number> weeklyBarChart;

    private AppTrackerDAO appTrackerDAO;

    // You need to assign a function to each of the buttons here
    @Override
    public void initialize() {
        appTrackerDAO = new AppTrackerDAO();
        System.out.println("Initialization complete");

        List<Pair<String, Integer>> dailyUsageData = appTrackerDAO.getDailyAppUsageData();
        List<Pair<String, Integer>> weeklyUsageData = appTrackerDAO.getWeeklyAppUsageData();


        Map<String, Integer> dailyUsageMap = new HashMap<>();
        Map<String, Integer> weeklyUsageMap = new HashMap<>();


        // Sort the appUsageData by usage in descending order
        Collections.sort(dailyUsageData, (a, b) -> b.getValue().compareTo(a.getValue()));
        Collections.sort(weeklyUsageData, (a, b) -> b.getValue().compareTo(a.getValue()));


        // Limit to top 6 apps
        dailyUsageData = dailyUsageData.subList(0, Math.min(6, dailyUsageData.size()));
        weeklyUsageData = weeklyUsageData.subList(0, Math.min(6, weeklyUsageData.size()));


        for (Pair<String, Integer> pair : dailyUsageData) {
            String appName = pair.getKey();
            int minutes = pair.getValue();

            if (appName != null) {

                if (appName.toLowerCase().endsWith(".exe")) {
                    appName = appName.substring(0, appName.length() - 4); // Remove the last 4 characters
                }

                if (appName.length() > 10) {
                    appName = appName.substring(0, 10); // Take the first 10 characters
                }

                // Populate daily usage map (assuming daily and weekly data is obtained differently)
                dailyUsageMap.put(appName, minutes);
            }
        }

        for (Pair<String, Integer> pair : weeklyUsageData) {
            String appName = pair.getKey();
            int minutes = pair.getValue();

            if (appName != null) {

                if (appName.toLowerCase().endsWith(".exe")) {
                    appName = appName.substring(0, appName.length() - 4); // Remove the last 4 characters
                }

                if (appName.length() > 10) {
                    appName = appName.substring(0, 10); // Take the first 10 characters
                }

                // Populate daily usage map (assuming daily and weekly data is obtained differently)
                weeklyUsageMap.put(appName, minutes);
            }
        }

        // Populate bar charts with the usage data
        populateBarChart(dailyBarChart, dailyUsageMap);
        populateBarChart(weeklyBarChart, weeklyUsageMap);

        styleBars(dailyBarChart);
        styleBars(weeklyBarChart);

        // Add handlers for the buttons
        settings.setOnAction(event -> handleNavButtonClick("Settings", settings));
        home.setOnAction(event -> handleNavButtonClick("index", home));
        reminders.setOnAction(event -> handleNavButtonClick("Reminders", reminders));
        restrictions.setOnAction(event -> handleNavButtonClick("Restrictions", restrictions));
        information.setOnAction(event -> handleNavButtonClick("ADHD_Information", information));
        report.setOnAction(event -> handleNavButtonClick("Screen_Time", report));
        logout.setOnAction(event -> handleNavButtonClick("login", logout));
    }

    private void populateBarChart(BarChart<String, Number> barChart, Map<String, Integer> usageMap) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Add data to the series
        for (Map.Entry<String, Integer> entry : usageMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Add the series to the bar chart
        barChart.getData().add(series);
    }

    private void styleBars(BarChart<String, Number> barChart) {
        for (XYChart.Series<String, Number> series : barChart.getData()) {
            for (XYChart.Data<String, Number> data : series.getData()) {
                // Get the bar node
                Node node = data.getNode();
                // Set the color for each bar programmatically
                node.setStyle("-fx-bar-fill: #EC5569");
            }
        }
    }

    @Override
    public void handleButtonClick(String buttonId) {
        System.out.println("Button clicked: " + buttonId);


    }

}
