package com.example.newplan;

import com.example.newplan.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.newplan.model.ProgramChecker;
import java.io.IOException;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class HelloApplication extends Application {
    private EventDAO eventDAO;
    private EventsManager eventsManager;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("index.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Present-Minds!");
        stage.setScene(scene);
        stage.show();
    }
    // Toms comment
    // Ethan did not commit here
    // Griffins second commit for webhook testing

    public static void main(String[] args) {
        // added this line quickly to see where the .db file was stored
        System.out.println("Working directory = " + System.getProperty("user.dir"));
        UserDAO userDAO = new UserDAO();
        userDAO.createTable();

        Test();
        EventDAO eventDAO = new EventDAO();
        EventsManager eventsManager = new EventsManager(eventDAO);
        AppTrackerDAO appTrackerDAO = new AppTrackerDAO();
        appTrackerDAO.createTable();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new ProgramChecker(eventDAO, appTrackerDAO, eventsManager), 0 ,5, TimeUnit.MINUTES);
        launch();
        userDAO.close();
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(new ProgramChecker(), 0, 5, TimeUnit.MINUTES);


    }
    static void Test(){
        EventDAO eventDAO = new EventDAO();
        EventsManager eventsManager = new EventsManager(eventDAO);
        eventDAO.createTable();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.set(2024, Calendar.MAY,10,10,30,0);
        cal2.set(2024, Calendar.MAY,10,16,30,0);
        Event event = new Event("Play Time", "Working today",cal,cal2,true,true);
        eventDAO.delete(1);
        eventDAO.insert(event,2);
        List<Event> events1 = eventDAO.getAllUser(1);
//        new ProgramChecker(eventDAO, eventsManager);

    }
}
