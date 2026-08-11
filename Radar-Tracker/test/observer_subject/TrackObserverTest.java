package observer_subject;

import org.junit.Test;
import static org.junit.Assert.*;

public class TrackObserverTest {
    @Test
    public void testIsInterface() {
        assertTrue(TrackObserver.class.isInterface());
    }
}
