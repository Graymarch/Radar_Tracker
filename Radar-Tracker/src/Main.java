import java.util.concurrent.TimeUnit;

import observer_subject.*;
import trackers.*;

public class Main{
	public static void main(String[] args) throws InterruptedException {
		TrackerBroadcaster broadcaster = new TrackerBroadcaster();
		broadcaster.subscribe(new ConsoleLogger());
		broadcaster.subscribe(new DefaultAnalyzer());
		
		TrackerFactory factory = new RandomTrackerFactory();
		
		Simulator simulator = new Simulator(factory, broadcaster);
		
		System.out.println("Starting radar track simulation (30s demo)...\n");
        simulator.start();

        TimeUnit.SECONDS.sleep(30);

        simulator.stop();
        System.out.println("\nSimulation stopped.");
	}
}