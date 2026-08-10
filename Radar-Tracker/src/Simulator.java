import trackers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import observer_subject.TrackerBroadcaster;

public class Simulator{
	
	private static final double TICK_SECONDS = 0.5;
	private static final int MAX_TRACKERS = 10;
	
	private final TrackerFactory factory;
	private final TrackerBroadcaster broadcaster;
	
	private final ArrayList<Tracker> activeTrackers = new ArrayList<>();
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	private final ExecutorService computePool = Executors.newFixedThreadPool(
	        Math.max(2, Math.min(4, Runtime.getRuntime().availableProcessors())));
	
	public Simulator(TrackerFactory factory, TrackerBroadcaster broadcaster) {
		this.factory = factory;
		this.broadcaster = broadcaster;
	}
	
	public void start() {
		scheduler.scheduleAtFixedRate(this::tick, 0, (long) (TICK_SECONDS * 1000), TimeUnit.MILLISECONDS);
	}
	
	public void stop() {
		scheduler.shutdown();
		computePool.shutdown();
	}
	
	public void tick() {
		maybeSpawnTrack();
		
		List<Callable<Tracker>> computeTasks = new ArrayList<>(activeTrackers.size());
        for (Tracker track : activeTrackers) {
            computeTasks.add(() -> {
                track.advance(TICK_SECONDS);

                return track;
            });
        }
        
        try {
        	List<Future<Tracker>> futures = computePool.invokeAll(computeTasks);
        	for (int i = 0; i < futures.size(); i++) {
                try {
                	futures.get(i).get();
                } catch (ExecutionException e) {
                    System.err.println("[ERROR] Track " + activeTrackers.get(i).getId()
                        + " compute failed, skipping this tick: " + e.getCause());
                }
            }
        }catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
            return;
        }
        
        for (Tracker tracker : activeTrackers) {
            broadcaster.broadcast(tracker);
        }
	}
	
	private void maybeSpawnTrack() {
		if(activeTrackers.isEmpty() || (activeTrackers.size() < MAX_TRACKERS && ThreadLocalRandom.current().nextDouble() < 0.3)) {
			activeTrackers.add(factory.createTracker());
		}
    }
}