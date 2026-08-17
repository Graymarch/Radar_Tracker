package observer_subject;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import enums.sizeClass;
import trackers.Tracker;

class SiteProtectionAnalyzerTest {

	@Test
	void testHeadingAnalysisReportSuccess() {
		SiteProtectionAnalyzer analyzer = new SiteProtectionAnalyzer();
		// ensure static sites map is cleared between tests
		try {
			java.lang.reflect.Field f = SiteProtectionAnalyzer.class.getDeclaredField("sites");
			f.setAccessible(true);
			((java.util.Map<?, ?>)f.get(null)).clear();
		} catch (Exception e) {
			// if reflection fails, tests may still run but could be flaky
		}
		// place a site at (2,0)
		analyzer.addSite(2.0, 0.0);

		// tracker previous state at (0,0) and current at (1,0)
		Tracker prev = new Tracker(1, 0.0, 0.0, 100.0, 0.0, sizeClass.SMALL);
		Tracker current = new Tracker(1, 1.0, 0.0, 100.0, 0.0, sizeClass.SMALL);

		// capture stdout
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(out));

		try {
			analyzer.onTrackUpdate(prev);    // first update stores previous state
			analyzer.onTrackUpdate(current); // second update should trigger analysis and report
		} finally {
			System.setOut(originalOut);
		}

		String output = out.toString();
		assertTrue(output.contains("Heading Anomaly"), "Expected heading anomaly to be reported");
		// site coordinates should appear in the message
		assertTrue(output.contains("(2.000000, 0.000000)"), "Expected site coordinates in report");
	}

	@Test
	void testHeadingAnalysisNoReportWhenNotOnCourse() {
		SiteProtectionAnalyzer analyzer = new SiteProtectionAnalyzer();
		// ensure static sites map is cleared between tests
		try {
			java.lang.reflect.Field f = SiteProtectionAnalyzer.class.getDeclaredField("sites");
			f.setAccessible(true);
			((java.util.Map<?, ?>)f.get(null)).clear();
		} catch (Exception e) {
			// ignore
		}
		// place a site at (3,0) so distances differ (prev->curr =1, curr->site=2)
		analyzer.addSite(3.0, 0.0);

		Tracker prev = new Tracker(2, 0.0, 0.0, 100.0, 0.0, sizeClass.SMALL);
		Tracker current = new Tracker(2, 1.0, 0.0, 100.0, 0.0, sizeClass.SMALL);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(out));

		try {
			analyzer.onTrackUpdate(prev);
			analyzer.onTrackUpdate(current);
		} finally {
			System.setOut(originalOut);
		}

		String output = out.toString();
		assertFalse(output.contains("Heading Anomaly"), "Did not expect a heading anomaly for this scenario");
	}

}
