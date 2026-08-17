package observer_subject;

import java.util.HashMap;
import java.util.Map;

import trackers.Tracker;
import trackers.Site;

public class SiteProtectionAnalyzer implements TrackObserver{
	
	private final static Map<Integer, Site> sites = new HashMap<>();
	
	private final Map<Integer, Tracker> previousStates = new HashMap<>();
	
	public void addSite(double x, double y) {
		Site newSite = new Site(x, y);
		sites.put(newSite.getId(), newSite);
	}

	@Override
	public void onTrackUpdate(Tracker tracker) {
		Tracker previousState = previousStates.get(tracker.getId());
		
		if(previousState != null) {
			sitesDangerAnalysis(tracker, previousState);
		}
		
		previousStates.put(tracker.getId(), new Tracker(tracker));
	}
	
	public void sitesDangerAnalysis(Tracker current, Tracker prev) {
		sites.forEach((_, site) -> {
			double deltaX = (current.getX() - prev.getX()) / 0.5;
			double deltaY = (current.getY() - prev.getY()) / 0.5;
			double speed = Math.hypot(deltaX, deltaY);
			double heading = Math.atan(speed);
			
			deltaX = (current.getX() - site.getX()) / 0.5;
			deltaY = (current.getY() - site.getY()) / 0.5;
			speed = Math.hypot(deltaX, deltaY);
			double headingToSite = Math.atan(speed);
			
			if(heading == headingToSite) {
				reportAnomaly(current.getId(), "Heading Anomaly. Craft is currently on course to Site[%d] at (%f, %f). Craft heading is %f radians".formatted(site.getId(), site.getX(), site.getY(), heading));
			}
		});
	}
}
