package observer_subject;

import classes.Tracker;

interface TrackObserver{
	public void onTrackUpdate(Tracker track);
}