package observer_subject;

import trackers.Tracker;

public class ConsoleLogger implements TrackObserver{

	@Override
	public void onTrackUpdate(Tracker tracker) {
		System.out.println(tracker.toString());
	}
}