import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import observer_subject.*;
import trackers.*;

public class SimulatorTest {
    @Test
    public void testTickSpawnsAndAdvances() {
        TrackerBroadcaster broadcaster = new TrackerBroadcaster();
        List<Tracker> observed = Collections.synchronizedList(new ArrayList<>());

        broadcaster.subscribe(new TrackObserver() {
            @Override
            public void onTrackUpdate(Tracker track) {
                // store a copy to avoid concurrent modification issues
                observed.add(new Tracker(track));
            }
        });

        TrackerFactory factory = new TrackerFactory() {
            @Override
            public Tracker createTracker() {
                return new Tracker(1, 0.0, 0.0, 100.0, 0.0, enums.sizeClass.SMALL);
            }
        };

        Simulator sim = new Simulator(factory, broadcaster);

        // Call tick twice; first tick must spawn at least one tracker, subsequent tick(s) advance it
        sim.tick();
        sim.tick();

        assertTrue("Expected at least two broadcasts across two ticks", observed.size() >= 2);

        double firstX = observed.get(0).getX();
        double lastX = observed.get(observed.size() - 1).getX();
        assertTrue("Tracker X position should increase after subsequent ticks", lastX > firstX);
    }
}
