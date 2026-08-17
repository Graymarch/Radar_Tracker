package trackers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enums.sizeClass;

public class RandomTrackerFactoryTest {
	//Only tests on publicly accessible data since some fields are private/protected.
    @Test
    public void testCreateTrackerProducesValidValues() {
        RandomTrackerFactory f = new RandomTrackerFactory();
        Tracker a = f.createTracker();
        Tracker b = f.createTracker();
        
        //Checks that the trackers were actually created. 
        assertNotNull(a);
        assertNotNull(b);
        
        //Checks if A: IDs are different, B: The IDs increment. 
        assertTrue(b.getId() > a.getId(), "IDs should increase");
        
        //Checks if the velocity is in the expected range. 
        assertTrue(a.getVelocity() >= 80.0 && a.getVelocity() <= 960.0);
        
        //Checks if the coordinates are in the expected range. 
        assertTrue(a.getX() >= -50.0 && a.getX() <= 50.0);
        assertTrue(a.getY() >= -50.0 && a.getY() <= 50.0);
        
        //checks if a sizeClass was given. 
        sizeClass s = a.getSize();
        assertNotNull(s);
    }
}
