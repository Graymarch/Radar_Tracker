package observer_subject;

import trackers.Tracker;

interface TrackObserver{
	public void onTrackUpdate(Tracker track);
}