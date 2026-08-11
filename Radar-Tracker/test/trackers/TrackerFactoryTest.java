package trackers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TrackerFactoryTest {
    @Test
    public void testCustomFactoryCreates() {
        TrackerFactory f = new TrackerFactory() {
            @Override
            public Tracker createTracker() {
                return new Tracker(7, 1.0, 2.0, 50.0, 1.0, enums.sizeClass.MEDIUM);
            }
        };
        Tracker t = f.createTracker();
        assertEquals(7, t.getId());
        assertEquals(1.0, t.getX(), 1e-6);
    }
}
