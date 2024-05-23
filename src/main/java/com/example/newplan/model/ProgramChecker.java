package com.example.newplan.model;

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
    private SessionManager sessionManager = SessionManager.getInstance();
    private List<String> restrictedPrograms;
    private List<String> lastOpenedApps;
    private List<String> ignoredPrograms;

    public ProgramChecker(EventDAO eventDAO, AppTrackerDAO appTrackerDAO, EventsManager eventsManager ){
        this.eventDAO = eventDAO;
        this.appTrackerDAO = appTrackerDAO;
        this.eventsManager = eventsManager;
        this.restrictedPrograms = new ArrayList<>();
        this.ignoredPrograms = new ArrayList<>();
        DefaultRestricedProccess();
        DefaultIgnoredProccess();
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

    public void DefaultIgnoredProccess(){
        AddIgnore("winlogon.exe");
        AddIgnore("wininit.exe");
        AddIgnore("unsecapp.exe");
        AddIgnore("uihost.exe");
        AddIgnore("tasklist.exe");
        AddIgnore("taskhostw.exe");
        AddIgnore("svchost.exe");
        AddIgnore("steamwebhelper.exe");
        AddIgnore("steamservice.exe");
        AddIgnore("sqlwriter.exe");
        AddIgnore("sqlservr.exe");
        AddIgnore("sqlceip.exe");
        AddIgnore("sppsvc.exe");
        AddIgnore("spoolsv.exe");
        AddIgnore("smartscreen.exe");
        AddIgnore("sihost.exe");
        AddIgnore("services.exe");
        AddIgnore("servicehost.exe");
        AddIgnore("officesvcmgr.exe");
        AddIgnore("msteamsupdate.exe");
        AddIgnore("msedgewebview2.exe");
        AddIgnore("mfevtps.exe");
        AddIgnore("mfemms.exe");
        AddIgnore("mcshield.exe");
        AddIgnore("mcoemmgr.exe");
        AddIgnore("mcapexe.exe");
        AddIgnore("mcafee-security.exe");
        AddIgnore("mcafee-security-ft.exe");
        AddIgnore("mc-webview-cnt.exe");
        AddIgnore("lsass.exe");
        AddIgnore("jhi_service.exe");
        AddIgnore("java.exe");
        AddIgnore("igfxCUIServiceN.exe");
        AddIgnore("gamingservicesnet.exe");
        AddIgnore("gamingservices.exe");
        AddIgnore("fsnotifier.exe");
        AddIgnore("fontdrvhost.exe");
        AddIgnore("figma_agent.exe");
        AddIgnore("explorer.exe");
        AddIgnore("esif_uf.exe");
        AddIgnore("dwm.exe");
        AddIgnore("dllhost.exe");
        AddIgnore("dasHost.exe");
        AddIgnore("ctfmon.exe");
        AddIgnore("csrss.exe");
        AddIgnore("conhost.exe");
        AddIgnore("cmd.exe");
        AddIgnore("backgroundTaskHost.exe");
        AddIgnore("audiodg.exe");
        AddIgnore("armsvc.exe");
        AddIgnore("XboxGameBarWidgets.exe");
        AddIgnore("WmiPrvSE.exe");
        AddIgnore("WmiApSrv.exe");
        AddIgnore("WindowsTerminal.exe");
        AddIgnore("Widgets.exe");
        AddIgnore("WidgetService.exe");
        AddIgnore("WUDFHost.exe");
        AddIgnore("WMIRegistrationService.exe");
        AddIgnore("VSSVC.exe");
        AddIgnore("UserOOBEBroker.exe");
        AddIgnore("TrustedInstaller.exe");
        AddIgnore("TiWorker.exe");
        AddIgnore("Taskmgr.exe");
        AddIgnore("SystemSettingsBroker.exe");
        AddIgnore("SystemSettingsAdminFlows.exe");
        AddIgnore("SystemSettings.exe");
        AddIgnore("System Idle Process");
        AddIgnore("System");
        AddIgnore("StartMenuExperienceHost.exe");
        AddIgnore("ShellExperienceHost.exe");
        AddIgnore("SecurityHealthSystray.exe");
        AddIgnore("SecurityHealthService.exe");
        AddIgnore("SearchIndexer.exe");
        AddIgnore("SearchHost.exe");
        AddIgnore("SSScheduler.exe");
        AddIgnore("SQLAGENT.EXE");
        AddIgnore("RuntimeBroker.exe");
        AddIgnore("RtkUWP.exe");
        AddIgnore("RtkBtManServ.exe");
        AddIgnore("RtkAudUService64.exe");
        AddIgnore("RstMwService.exe");
        AddIgnore("Registry");
        AddIgnore("RdClient.Windows.exe");
        AddIgnore("ProtectedModuleHost.exe");
        AddIgnore("PhoneExperienceHost.exe");
        AddIgnore("PEFService.exe");
        AddIgnore("OpenConsole.exe");
        AddIgnore("OobeHook.exe");
        AddIgnore("OneDrive.exe");
        AddIgnore("OneApp.IGCC.WinService.exe");
        AddIgnore("OfficeClickToRun.exe");
        AddIgnore("ONENOTEM.EXE");
        AddIgnore("NisSrv.exe");
        AddIgnore("MsMpEng.exe");
        AddIgnore("MpDefenderCoreService.exe");
        AddIgnore("ModuleCoreService.exe");
        AddIgnore("MoUsoCoreWorker.exe");
        AddIgnore("MicrosoftEdgeUpdate.exe");
        AddIgnore("MfeAVSvc.exe");
        AddIgnore("Memory Compression");
        AddIgnore("McUpdaterModule.exe");
        AddIgnore("McCSPServiceHost.exe");
        AddIgnore("MMSSHOST.exe");
        AddIgnore("LogonUI.exe");
        AddIgnore("LockApp.exe");
        AddIgnore("LocationNotificationWindows.exe");
        AddIgnore("IntelCpHDCPSvc.exe");
        AddIgnore("IntelAudioService.exe");
        AddIgnore("HxTsr.exe");
        AddIgnore("GameBar.exe");
        AddIgnore("FileOpenManager64.exe");
        AddIgnore("FileOpenBroker64.exe");
        AddIgnore("FileCoAuth.exe");
        AddIgnore("DtsApo4Service.exe");
        AddIgnore("DADUpdater.exe");
        AddIgnore("AsusWiFiSmartConnect.exe");
        AddIgnore("AsusSystemDiagnosis.exe");
        AddIgnore("AsusSystemAnalysis.exe");
        AddIgnore("AsusSwitch.exe");
        AddIgnore("AsusSoftwareManagerAgent.exe");
        AddIgnore("AsusSoftwareManager.exe");
        AddIgnore("AsusScreenXpertUI.exe");
        AddIgnore("AsusScreenXpertReunion.exe");
        AddIgnore("AsusScreenXpertHostService.exe");
        AddIgnore("AsusOptimizationStartupTask.exe");
        AddIgnore("AsusOptimization.exe");
        AddIgnore("AsusOSD.exe");
        AddIgnore("AsusAppService.exe");
        AddIgnore("ApplicationFrameHost.exe");
        AddIgnore("AggregatorHost.exe");

    }
    public void AddRestriction(String processName){
        if (!restrictedPrograms.contains(processName)){
            restrictedPrograms.add(processName);
        }
    }
    public void RemoveRestriction(String processName){
        restrictedPrograms.remove(processName);
    }

    public void AddIgnore(String processName){
        if (!ignoredPrograms.contains(processName)){
            ignoredPrograms.add(processName);
        }
    }
    public void RemoveIgnore(String processName){
        ignoredPrograms.remove(processName);
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

    public List<String> ignorePrograms(List<String> runningPrograms) {
        List<String> newList = new ArrayList<>();
        for (String app: runningPrograms) {
            if (!ignoredPrograms.contains(app)) {
                newList.add(app);
            }
        }
        return newList;
    }

    public void updateAppTracker() {
        if (lastOpenedApps == null) {
            lastOpenedApps = ignorePrograms(getRunningProcesses());
        } else {
            List<String> runningApps = ignorePrograms(getRunningProcesses());
            int appCount = 0;
            for (String app: runningApps) {
                if (lastOpenedApps.contains(app)) {
                    appTrackerDAO.update(app);
                    appCount++;
                }
            }
            // update last opened apps
            System.out.println(appCount + " apps have been updated in the App Tracker for user with ID " + sessionManager.getUserId());
            lastOpenedApps = runningApps;
        }
    }
}

