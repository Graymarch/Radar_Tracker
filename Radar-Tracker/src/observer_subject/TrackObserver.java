package observer_subject;

import trackers.Tracker;

public interface TrackObserver{
	public void onTrackUpdate(Tracker track);
	
	public default void reportAnomaly(int id, String description) {
		System.out.println(String.format("[Anomaly]: Track-%d, Description: %s", id, description));
	}
}