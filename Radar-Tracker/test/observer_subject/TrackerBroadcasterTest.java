package observer_subject;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;

import trackers.Tracker;
import enums.sizeClass;

public class TrackerBroadcasterTest {
    @Test
    public void testSubscribeBroadcastAndRemove() {
        TrackerBroadcaster b = new TrackerBroadcaster();
        AtomicInteger counterA = new AtomicInteger(0);
        AtomicInteger counterB = new AtomicInteger(0);

        TrackObserver a = new TrackObserver() {
            @Override
            public void onTrackUpdate(Tracker track) { counterA.incrementAndGet(); }
        };

        TrackObserver c = new TrackObserver() {
            @Override
            public void onTrackUpdate(Tracker track) { counterB.incrementAndGet(); }
        };

        b.subscribe(a);
        b.subscribe(c);

        Tracker t = new Tracker(1, 0, 0, 100, 0, sizeClass.SMALL);
        b.broadcast(t);
        assertEquals(1, counterA.get());
        assertEquals(1, counterB.get());

        b.remove(a);
        b.broadcast(t);
        assertEquals(1, counterA.get());
        assertEquals(2, counterB.get());
    }
}
