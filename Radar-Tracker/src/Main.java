import java.util.concurrent.TimeUnit;

import observer_subject.*;
import trackers.*;

public class Main{
	public static void main(String[] args) throws InterruptedException {
		//Creates a SiteProtectionAnalyzer with a few example sites. 
		//This Analyzer is created separately so that sites can be added to it. 
		SiteProtectionAnalyzer siteAnalyzer = new SiteProtectionAnalyzer();
		siteAnalyzer.addSite(1000, 1000);
		siteAnalyzer.addSite(-1000, 1000);
		siteAnalyzer.addSite(1000, -1000);
		siteAnalyzer.addSite(-1000, -1000);
		
		//Creates the broadcaster and subscribes the observers to it. 
		TrackerBroadcaster broadcaster = new TrackerBroadcaster();
		broadcaster.subscribe(new ConsoleLogger());
		broadcaster.subscribe(new SpeedAnalyzer());
		broadcaster.subscribe(siteAnalyzer);
		
		//Creates the factory to make Trackers. 
		TrackerFactory factory = new RandomTrackerFactory();
		
		//Creates the simulator with the factory and broadcaster initialized above. 
		Simulator simulator = new Simulator(factory, broadcaster);
		
		//Runs the simulator for 30 seconds, then stops. 
		System.out.println("Starting radar track simulation (30s demo)...\n");
        simulator.start();

        TimeUnit.SECONDS.sleep(30);

        simulator.stop();
        System.out.println("\nSimulation stopped.");
	}
}