package observer_subject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import enums.sizeClass;
import trackers.Tracker;

public class ConsoleLoggerTest {
    @Test
    public void testOnTrackUpdatePrintsTracker() {
        ConsoleLogger logger = new ConsoleLogger();
        Tracker t = new Tracker(5, 1.0, 2.0, 100.0, 0.0, sizeClass.SMALL);

        //Captures the output by redirecting System.out to an output stream. 
        //After the output is captured, the original System.out is restored. 
        //A try/finally block is used to ensure the system output is restored properly. 
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            logger.onTrackUpdate(t);
        } finally {
            System.setOut(originalOut);
        }

        //Checks if the logger printed the right object by checking for expected keywords in the output. 
        String printed = out.toString();
        assertTrue(printed.contains("Object 5") || printed.contains("5"));
    }
}
