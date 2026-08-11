package observer_subject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import enums.sizeClass;
import trackers.Tracker;

public class DefaultAnalyzerTest {
    @Test
    public void testSpeedAnalysisReportsAnomalyWhenOutsideThreshold() {
        DefaultAnalyzer analyzer = new DefaultAnalyzer();

        // Create a tracker with speed below SMALL threshold (170)
        Tracker t = new Tracker(99, 0.0, 0.0, 50.0, 0.0, sizeClass.SMALL);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            analyzer.speedAnalysis(t);
        } finally {
            System.setOut(originalOut);
        }

        String printed = out.toString();
        assertTrue(printed.contains("Anomaly") || printed.contains("Speed Anomaly"), "Expected anomaly message to be printed");
    }
}
