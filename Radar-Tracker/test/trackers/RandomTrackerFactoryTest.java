package trackers;

import org.junit.Test;
import static org.junit.Assert.*;

import enums.sizeClass;

public class RandomTrackerFactoryTest {
    @Test
    public void testCreateTrackerProducesValidValues() {
        RandomTrackerFactory f = new RandomTrackerFactory();
        Tracker a = f.createTracker();
        Tracker b = f.createTracker();
        assertNotNull(a);
        assertNotNull(b);
        assertTrue("IDs should increase", b.getId() > a.getId());
        assertTrue(a.getVelocity() >= 80.0 && a.getVelocity() <= 960.0);
        assertTrue(a.getX() >= -50.0 && a.getX() <= 50.0);
        assertTrue(a.getY() >= -50.0 && a.getY() <= 50.0);
        // size should be one of the enum values
        sizeClass s = a.getSize();
        assertNotNull(s);
    }
}
