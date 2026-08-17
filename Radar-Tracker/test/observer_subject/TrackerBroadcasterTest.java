package observer_subject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import enums.sizeClass;
import trackers.Tracker;

public class TrackerBroadcasterTest {
    @Test
    public void testSubscribeBroadcastAndRemove() {
        TrackerBroadcaster c = new TrackerBroadcaster();
        AtomicInteger counterA = new AtomicInteger(0);
        AtomicInteger counterB = new AtomicInteger(0);

        //Subscribes a pair of anonymous implementations of the TrackObserver interface to check the broadcaster's functions. 
        //Whenever they receive a broadcast, they should increment their respective counters. 
        TrackObserver a = new TrackObserver() {
            @Override
            public void onTrackUpdate(Tracker track) { counterA.incrementAndGet(); }
        };

        TrackObserver b = new TrackObserver() {
            @Override
            public void onTrackUpdate(Tracker track) { counterB.incrementAndGet(); }
        };

        c.subscribe(a);
        c.subscribe(b);

        //Checks that both subscribers receive the broadcast. 
        Tracker t = new Tracker(1, 0, 0, 100, 0, sizeClass.SMALL);
        c.broadcast(t);
        assertEquals(1, counterA.get());
        assertEquals(1, counterB.get());

        //Removes a subscriber and broadcasts to ensure the removal was successful. 
        c.remove(a);
        c.broadcast(t);
        assertEquals(1, counterA.get());
        assertEquals(2, counterB.get());
    }
}
