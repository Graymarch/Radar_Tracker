package observer_subject;

import trackers.Tracker;

public interface TrackObserver{
	public void onTrackUpdate(Tracker track);
}