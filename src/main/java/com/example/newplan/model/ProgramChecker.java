package com.example.newplan.model;

import com.example.newplan.AppTrackerDAO;
import com.example.newplan.Event;
import com.example.newplan.EventDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProgramChecker implements Runnable{
    private EventDAO eventDAO;
    private AppTrackerDAO appTrackerDAO;
    private EventsManager eventsManager;
    private List<String> restrictedPrograms;
    private List<String> lastOpenedApps;

    public ProgramChecker(EventDAO eventDAO, AppTrackerDAO appTrackerDAO, EventsManager eventsManager ){
        this.eventDAO = eventDAO;
        this.appTrackerDAO = appTrackerDAO;
        this.eventsManager = eventsManager;
        this.restrictedPrograms = new ArrayList<>();
        DefaultRestricedProccess();
    }
    public List<String> GetRestrictedPrograms(){
        return restrictedPrograms;
    }

    @Override
    public void run() {
        eventDAO = new EventDAO();
        eventsManager = new EventsManager(eventDAO);
        updateAppTracker();
        if (restrictionActive()){
            List<String> restrictedRunning = getRunningRestrictedProcesses(restrictedPrograms);
            List<String> list = new ArrayList<String>();
            list.add("");
            if (!restrictedRunning.equals(list)){
                DisplayWarning(restrictedRunning);
                ClosePrograms(restrictedRunning);
            }
        }


    }
    void DisplayWarning(List<String> restrictedRunning){
        return;
    }

   public void ClosePrograms(List<String> restrictedRunning){
        for(String processName: restrictedRunning){
            try {
                Process process = Runtime.getRuntime().exec("taskkill /F /IM " + processName);
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println(processName + " has been closed successfully.");
                } else {
                    System.out.println("Failed to close " + processName + ".");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Event> restrictionList(){
        return eventsManager.getAllRestrictions();
    }

    private boolean restrictionActive(){
        return restrictionList() != null;
    }
    private List<Event> isProcessRunning(String processName) {
        // Implement process checking logic here
        return null; // Placeholder return value
    }
    public void DefaultRestricedProccess(){
        AddRestriction("NotePad");
        AddRestriction("Minecraft");
        AddRestriction("EXCEL.EXE");
    }
    public void AddRestriction(String processName){
        if (!restrictedPrograms.contains(processName)){
            restrictedPrograms.add(processName);
        }
    }
    public void RemoveRestriction(String processName){
        restrictedPrograms.remove(processName);
    }

    public static List<String> getRunningRestrictedProcesses(List<String> processNames) {
        Set<String> uniqueProcesses = new HashSet<>();
        try {
            Process process = Runtime.getRuntime().exec("tasklist /fo csv /nh");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                for (String processName : processNames) {
                    if (line.contains(processName)) {
                        uniqueProcesses.add(processName);
                        break;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(uniqueProcesses);
    }
    public static List<String> getRunningProcesses() {
        Set<String> uniqueProcesses = new HashSet<>();
        try {
            Process process = Runtime.getRuntime().exec("tasklist /fo csv /nh");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse each line to extract the process name and add it to the set
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    uniqueProcesses.add(parts[0].replace("\"", ""));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(uniqueProcesses);
    }

    public void updateAppTracker() {
        if (lastOpenedApps == null) {
            lastOpenedApps = getRunningProcesses();
        } else {
            List<String> runningApps = getRunningProcesses();
            for (String app: runningApps) {
                if (lastOpenedApps.contains(app)) {
                    appTrackerDAO.update(app);
                }
            }
            // update last opened apps
            lastOpenedApps = runningApps;
        }
    }
}

