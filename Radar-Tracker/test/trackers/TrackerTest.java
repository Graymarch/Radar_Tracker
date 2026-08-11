package trackers;

import org.junit.Test;
import static org.junit.Assert.*;

import enums.sizeClass;

public class TrackerTest {
    @Test
    public void testAdvanceAndGetters() throws InterruptedException {
        Tracker t = new Tracker(42, 0.0, 0.0, 100.0, 0.0, sizeClass.SMALL);
        double beforeX = t.getX();
        long beforeUpdate = t.getLastUpdate();
        Thread.sleep(1);
        t.advance(0.5); // expected to move x by velocity * seconds when heading==0
        assertTrue(t.getX() > beforeX);
        assertTrue(t.getLastUpdate() >= beforeUpdate);
        String s = t.toString();
        assertTrue(s.contains("Object 42") || s.contains("42"));
    }
}
