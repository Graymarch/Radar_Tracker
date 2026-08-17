package trackers;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.*;
import enums.sizeClass;

//Produces Trackers with randomized attributes. 
public class RandomTrackerFactory implements TrackerFactory{

	//Ensures no two trackers made by this factory will have the same id. 
	private final static AtomicInteger nextId = new AtomicInteger(1000);
	
	@Override
	//Creates and returns a new tracker. 
	public Tracker createTracker() {
		ThreadLocalRandom rnd = ThreadLocalRandom.current();
		sizeClass[] sizeOptions = sizeClass.values(); //Stores the sizeClass enums in an array for random selection. 

        double x = rnd.nextDouble(-50, 50);
        double y = rnd.nextDouble(-50, 50);
        double heading = rnd.nextDouble(0, 2 * Math.PI); //radians
        double velocity = rnd.nextDouble(80, 960);  // knots: airliner to fast jet
        sizeClass size = sizeOptions[rnd.nextInt(sizeOptions.length)];
        
		return new Tracker(nextId.getAndIncrement(), x, y, velocity, heading, size);
	}
	
}