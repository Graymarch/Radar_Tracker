package observer_subject;

import trackers.Tracker;

//Interface for observers that can be subscribed to TrackerBroadcaster. 
public interface TrackObserver{
	//Abstract method for handling broadcasts. 
	public void onTrackUpdate(Tracker track);
	
	//Concrete implementation for standard anomaly report behavior. 
	public default void reportAnomaly(int id, String description) {
		System.out.println(String.format("[Anomaly]: Track-%d, Description: %s", id, description));
	}
}