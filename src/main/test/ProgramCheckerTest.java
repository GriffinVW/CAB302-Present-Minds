import com.example.newplan.EventDAO;
import com.example.newplan.model.EventsManager;
import com.example.newplan.model.ProgramChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.newplan.model.ProgramChecker.getRunningRestrictedProcesses;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgramCheckerTest {
    private ProgramChecker programChecker;
    private EventDAO eventDAO;
    private EventsManager eventsManager;
    @BeforeEach
    public void setUp() {
        programChecker = new ProgramChecker(eventDAO, eventsManager);


    }
    @Test
    public void testCompareRunning() {
        programChecker.setApps();
        List<String> processNames = List.of("EXCEL.EXE", "chrome.exe");
        List<String> running = getRunningRestrictedProcesses(processNames);
        List<String> actual = List.of("chrome.exe");
        assertEquals(actual, running);
    }
    @Test
    public void CloseRunning(){
        List<String> processNames = List.of("EXCEL.EXE");
        programChecker.ClosePrograms(processNames);

    }
}
