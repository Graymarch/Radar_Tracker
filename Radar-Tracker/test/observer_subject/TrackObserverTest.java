package observer_subject;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TrackObserverTest {
    @Test
    public void testIsInterface() {
        assertTrue(TrackObserver.class.isInterface());
    }
}
