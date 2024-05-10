package com.example.newplan.model;

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
    private EventsManager eventsManager;
    private List<String> restrictedPrograms;

    public ProgramChecker(EventDAO eventDAO, EventsManager eventsManager ){
        this.eventDAO = eventDAO;
        this.eventsManager = eventsManager;
    }
    public List<String> GetRestrictedPrograms(){
        return restrictedPrograms;
    }

    @Override
    public void run() {
        eventDAO = new EventDAO();
        eventsManager = new EventsManager(eventDAO);
        if (restrictionActive()){
            setApps();
            List<String> restrictedRunning = getRunningProcesses(restrictedPrograms);
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
    public void setApps(){
        restrictedPrograms = new ArrayList<>(); // Replace with your restricted program name
        restrictedPrograms.add("NotePad");
        restrictedPrograms.add("Minecraft");
        restrictedPrograms.add("EXCEL.EXE");
    }
    public static List<String> getRunningProcesses(List<String> processNames) {
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
}

