package trackers;

//An interface for factory classes that produce trackers. 
//Future implementations will have more factories such as one that builds from a scenario file. 
public interface TrackerFactory{
	public Tracker createTracker();
}