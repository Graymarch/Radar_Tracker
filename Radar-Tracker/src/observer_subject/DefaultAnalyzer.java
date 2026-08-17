package observer_subject;

import java.util.HashMap;
import java.util.Map;

import enums.sizeClass;
import trackers.Tracker;

public class DefaultAnalyzer implements TrackObserver{
	private final Map<Integer, Tracker> previousStates = new HashMap<>();
	
	private static Map<sizeClass, int[]> speedThresholds = new HashMap<>(Map.of(sizeClass.SMALL, new int[]{170, 250}, sizeClass.MEDIUM, new int[] {420, 760}, sizeClass.LARGE, new int[] {760, 960}));

	@Override
	public void onTrackUpdate(Tracker tracker) {
		Tracker previousState = previousStates.get(tracker.getId());
		
		if(previousState != null) {
			speedAnalysis(tracker);
		}
		
		previousStates.put(tracker.getId(), new Tracker(tracker));
	}
	
	public void speedAnalysis(Tracker tracker) {
		int[] speedRange = speedThresholds.get(tracker.getSize());
		Tracker prev = previousStates.get(tracker.getId());
		
		double velocityX = (tracker.getX() - prev.getX()) / 0.5;
		double velocityY = (tracker.getY() - prev.getY()) / 0.5;
		double speed = Math.hypot(velocityX, velocityY);
		
		if(speed < speedRange[0] || speed > speedRange[1]) {
			reportAnomaly(tracker.getId(), String.format("Speed Anomaly. Expected range %d ft/s - %d ft/s. Actual speed: %f ft/s.", speedRange[0], speedRange[1], speed));
		}
	}
}