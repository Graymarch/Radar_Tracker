package observer_subject;

import java.util.HashMap;
import java.util.Map;

import enums.sizeClass;
import trackers.Tracker;

//Analyzes a craft's speed and flags anomalies if the craft's speed is outside the expected range. 

/*
 * Anomaly Implications:
 * - The radar (simulator in this instance) may be reporting an incorrect size. Implications:
 * 		- Radar is malfunctioning.
 * 		- The craft is deliberately obfuscating its size. 
 * - A small size with a high speed may indicate military craft such as jets. 
*/
public class SpeedAnalyzer implements TrackObserver{
	//Tracks the previous state of each Tracker. 
	private final static Map<Integer, Tracker> previousStates = new HashMap<>();
	
	//Map of expected speed ranges for each size class in ft/s. 
	private static Map<sizeClass, int[]> speedThresholds = new HashMap<>(Map.of(sizeClass.SMALL, new int[]{170, 250}, sizeClass.MEDIUM, new int[] {420, 760}, sizeClass.LARGE, new int[] {760, 960}));

	@Override
	//Analyzes the craft's speed on each update. 
	public void onTrackUpdate(Tracker tracker) {
		//Checks for a previous state on that tracker. 
		Tracker previousState = previousStates.get(tracker.getId());
		
		//If the tracker has a previous state (it didn't just spawn) it analyzes the tracker's speed. 
		if(previousState != null) {
			speedAnalysis(tracker, previousState);
		}
		
		//Saves the current state as the previous state. 
		previousStates.put(tracker.getId(), new Tracker(tracker));
	}
	
	//Analyzes the craft's speed by seeing if it falls within the expected range. 
	public void speedAnalysis(Tracker current, Tracker prev) {
		int[] speedRange = speedThresholds.get(current.getSize());
		
		//Calculates the tracker's speed by taking the hypotenuse of the x and y velocities. 
		double velocityX = (current.getX() - prev.getX()) / 0.5;
		double velocityY = (current.getY() - prev.getY()) / 0.5;
		double speed = Math.hypot(velocityX, velocityY);
		
		//Prints an anomaly report to the console if the speed is outside the expected range. 
		if(speed < speedRange[0] || speed > speedRange[1]) {
			reportAnomaly(current.getId(), String.format("Speed Anomaly. Expected range %d ft/s - %d ft/s. Actual speed: %f ft/s.", speedRange[0], speedRange[1], speed));
		}
	}
}