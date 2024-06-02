package com.example.newplan;

import com.example.newplan.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.example.newplan.model.ProgramChecker;
import java.io.IOException;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SessionManager sessionManager = SessionManager.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle("Present-Minds!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // added this line quickly to see where the .db file was stored
        System.out.println("Working directory = " + System.getProperty("user.dir"));

        // Creates user connection and table
        UserDAO userDAO = new UserDAO();
        userDAO.createTable();

        //Test();

        //Creates event table connection and event manager
        EventDAO eventDAO = new EventDAO();
        EventsManager eventsManager = new EventsManager(eventDAO);

        //Creates app tracker connection and table
        AppTrackerDAO appTrackerDAO = new AppTrackerDAO();
        appTrackerDAO.createTable();

        //Initiates loop of 5 minutes
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new ProgramChecker(eventDAO, appTrackerDAO, eventsManager), 0 ,5, TimeUnit.MINUTES);

        //Launches Application
        launch();

        //Closes data connections and loop upon app close
        userDAO.close();
        appTrackerDAO.close();
        eventDAO.close();
        scheduler.shutdown();
    }

    //Test class for Events
    static void Test(){
        EventDAO eventDAO = new EventDAO();
        EventsManager eventsManager = new EventsManager(eventDAO);
        eventDAO.createTable();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.set(2024, Calendar.MAY,10,10,30,0);
        cal2.set(2024, Calendar.MAY,10,16,30,0);
        Event event = new Event("Play Time", "Working today",cal,cal2,true,true);
        eventDAO.insert(event,2);
        List<Event> events1 = eventDAO.getAllUser(1);
    }
}
