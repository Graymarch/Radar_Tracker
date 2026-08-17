package observer_subject;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import enums.sizeClass;
import trackers.Tracker;

public class SpeedAnalyzerTest {
	//Tests that the analyzer flags anomalies correctly. 
    @Test
    public void testSpeedAnalysisReportsAnomalyWhenOutsideThreshold() {
        SpeedAnalyzer analyzer = new SpeedAnalyzer();

        // Create a tracker with speed below SMALL threshold (170)
        Tracker t = new Tracker(99, 0.0, 0.0, 50.0, 0.0, sizeClass.SMALL);

        //Captures the output by redirecting System.out to an output stream. 
        //After the output is captured, the original System.out is restored. 
        //A try/finally block is used to ensure the system output is restored properly. 
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            analyzer.onTrackUpdate(t);
            t.advance(0.5);
            analyzer.onTrackUpdate(t);
        } finally {
            System.setOut(originalOut);
        }

        //Checks if the analysis caught the anomaly by checking the output for expected keywords. 
        String printed = out.toString();
        assertTrue(printed.contains("Anomaly") || printed.contains("Speed Anomaly"), "Expected anomaly message to be printed");
    }
    
    //Tests that the analyzer doesn't flag anomalies on the first tick. 
    //Essentially testing that the if(previousState != null) conditional is working properly. 
    @Test
    public void testNoAnomalyOnFirstAnalysis() {
    	SpeedAnalyzer analyzer = new SpeedAnalyzer();

        // Create a tracker with speed below SMALL threshold (170)
        Tracker t = new Tracker(99, 0.0, 0.0, 50.0, 0.0, sizeClass.SMALL);

        //Captures the output by redirecting System.out to an output stream. 
        //After the output is captured, the original System.out is restored. 
        //A try/finally block is used to ensure the system output is restored properly. 
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            analyzer.onTrackUpdate(t);
        } finally {
            System.setOut(originalOut);
        }

      //Checks if the analysis erroneously caught an anomaly by checking the output for unexpected keywords.
        String printed = out.toString();
        assertTrue(!printed.contains("Anomaly") && !printed.contains("Speed Anomaly"), "No anomaly should be reported.");
    }
}
