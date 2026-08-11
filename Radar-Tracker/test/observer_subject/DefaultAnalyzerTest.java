package observer_subject;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import trackers.Tracker;
import enums.sizeClass;

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
        assertTrue("Expected anomaly message to be printed", printed.contains("Anomaly") || printed.contains("Speed Anomaly"));
    }
}
