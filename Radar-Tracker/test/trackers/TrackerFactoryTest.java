package trackers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TrackerFactoryTest {
    @Test
    public void testCustomFactoryCreates() {
    	assertTrue(TrackerFactory.class.isInterface());
    }
}