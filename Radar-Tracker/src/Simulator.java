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
	
	private static final double TICK_SECONDS = 0.5; //Defines how long each tick is. 
	private static final int MAX_TRACKERS = 10; //The number of craft that are simulated simultaneously. 
	
	private final TrackerFactory factory; //Factory to create new Trackers. 
	private final TrackerBroadcaster broadcaster; //Broadcasts updates to observers on each tick. 
	
	private final ArrayList<Tracker> activeTrackers = new ArrayList<>(); //Holds active trackers. 
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(); //Schedules the tick method to run at a rate determined by TICK_SECONDS.
	//The computePool is used to parallelize tracker updates in the tick method. The number of threads is determined by the number of processors available.
	//The pool will have at least 2 threads and as many as 4. 
	private final ExecutorService computePool = Executors.newFixedThreadPool(
	        Math.max(2, Math.min(4, Runtime.getRuntime().availableProcessors())));
	
	//Constructor. 
	public Simulator(TrackerFactory factory, TrackerBroadcaster broadcaster) {
		this.factory = factory;
		this.broadcaster = broadcaster;
	}
	
	//Starts the simulator by scheduling the tick method. 
	public void start() {
		scheduler.scheduleAtFixedRate(this::tick, 0, (long) (TICK_SECONDS * 1000), TimeUnit.MILLISECONDS);
	}
	
	//Stops the simulator by stopping the scheduler and the compurePool. ComputePool uses shutdownNow to prevent further executions after the shutdown message is sent. 
	public void stop() {
		scheduler.shutdown();
		computePool.shutdownNow();
	}
	
	//Updates all active trackers and broadcasts their new state to observers.
	public void tick() {
		//A new track may be created.
		maybeSpawnTrack();
		
		//Creates a list of tasks as callables so they can execute in parallel. 
		//At this scale, parallelization doesn't have a meaningful impact on performance. This just demonstrates that, at scale, it can be parallelized. 
		//Parallelization would also be more useful if/when the Trackers gain more complex behavior.
		List<Callable<Tracker>> computeTasks = new ArrayList<>(activeTrackers.size());
        for (Tracker track : activeTrackers) {
            computeTasks.add(() -> {
                track.advance(TICK_SECONDS);

                return track;
            });
        }
        
        //Invokes the callable tasks from computeTasks in parallel using computePool. 
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
        
        //Broadcasts the trackers new state to observers.
        activeTrackers.forEach(tracker -> {
            broadcaster.broadcast(tracker);
        });
	}
	
	//Appx. 30% change to spawn a new track if there is room. 
	//Guarantees that at least one track is spawned when the simulator starts.
	private void maybeSpawnTrack() {
		if(activeTrackers.isEmpty() || (activeTrackers.size() < MAX_TRACKERS && ThreadLocalRandom.current().nextDouble() < 0.3)) {
			activeTrackers.add(factory.createTracker());
		}
    }
}