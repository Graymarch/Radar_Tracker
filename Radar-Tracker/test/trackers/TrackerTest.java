package trackers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import enums.sizeClass;

public class TrackerTest {
    @Test
    public void testAdvanceAndGetters() throws InterruptedException {
        Tracker t = new Tracker(42, 0.0, 0.0, 100.0, 0.0, sizeClass.SMALL);
        
        //Collects current state of the Tracker. 
        double beforeX = t.getX();
        long beforeUpdate = t.getLastUpdate();
        
        //Expected to move x by velocity * seconds when heading==0
        t.advance(0.5); 
        
        //Checks that the tracker moved and the time stamp updated. 
        assertTrue(t.getX() > beforeX);
        assertTrue(t.getLastUpdate() >= beforeUpdate);
        
        //Checks if the toString is working properly and the id isn't modified. 
        String s = t.toString();
        assertTrue(s.contains("Object 42") || s.contains("42"));
    }
}
