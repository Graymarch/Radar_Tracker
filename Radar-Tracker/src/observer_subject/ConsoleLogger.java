package observer_subject;

import trackers.Tracker;

//Simply prints a tracker's current state to the console. 
public class ConsoleLogger implements TrackObserver{

	@Override
	public void onTrackUpdate(Tracker tracker) {
		System.out.println(tracker.toString());
	}
}