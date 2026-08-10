package trackers;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.*;
import enums.sizeClass;

public class RandomTrackerFactory implements TrackerFactory{

	private final AtomicInteger nextId = new AtomicInteger(1000);
	
	@Override
	public Tracker createTracker() {
		ThreadLocalRandom rnd = ThreadLocalRandom.current();
		sizeClass[] sizeOptions = sizeClass.values();

        double x = rnd.nextDouble(-50, 50);      // nm
        double y = rnd.nextDouble(-50, 50);      // nm
        double heading = rnd.nextDouble(0, 2 * Math.PI); //radians
        double velocity = rnd.nextDouble(80, 960);  // knots: airliner to fast jet
        sizeClass size = sizeOptions[rnd.nextInt(sizeOptions.length)];
        
		return new Tracker(nextId.getAndIncrement(), x, y, velocity, heading, size);
	}
	
}