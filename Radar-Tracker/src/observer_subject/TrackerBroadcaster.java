package observer_subject;

import java.util.ArrayList;
import java.util.List;
import trackers.Tracker;

//Broadcasters can have TrackObserver objects subscribed to them. 
//Whenever broadcast is executed, all observers execute their onTrackUpdate method. 
public class TrackerBroadcaster{ 
	private final ArrayList<TrackObserver> observers = new ArrayList<>();
	
	public boolean subscribe(TrackObserver observer) {
		return observers.add(observer);
	}
	
	public boolean remove(TrackObserver observer) {
		return observers.remove(observer);
	}
	
	public void broadcast(Tracker tracker) {
		for(TrackObserver i : observers) {
			i.onTrackUpdate(tracker);
		}
	}
	
	public List<TrackObserver> getObservers(){
		return new ArrayList<TrackObserver>(observers);
	}
}