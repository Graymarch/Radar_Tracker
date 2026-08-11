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

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            logger.onTrackUpdate(t);
        } finally {
            System.setOut(originalOut);
        }

        String printed = out.toString();
        assertTrue(printed.contains("Object 5") || printed.contains("5"));
    }
}
