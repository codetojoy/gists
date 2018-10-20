
package net.codetojoy;

import org.junit.*;
import static org.junit.Assert.*;

public class TimerTestCase {
    private Ping ping = new Ping();

    @Test
    public void testGetElapsedInMillis() {
        Timer timer = new Timer();
        long delayInMillis = 50L;
        ping.doWait(delayInMillis);

        // test
        long result = timer.getElapsedInMillis();

        assertTrue(result > delayInMillis);
        assertTrue(result < (3 * delayInMillis));
    }

    @Test
    public void testIsPastThreshold_Longer() {
        int thresholdInSeconds = 1;
        Timer timer = new Timer(thresholdInSeconds);
        long delayInMillis = (thresholdInSeconds * 1000L) + 25L;
        ping.doWait(delayInMillis);

        // test
        boolean result = timer.isPastThreshold();

        assertTrue(result);
    }

    @Test
    public void testIsPastThreshold_Shorter() {
        Timer timer = new Timer();
        long delayInMillis = 75L;
        ping.doWait(delayInMillis);

        // test
        boolean result = timer.isPastThreshold();

        assertFalse(result);
    }

    @Test
    public void testGetElapsed() {
        Timer timer = new Timer();

        // test
        String result = timer.getElapsed("message");

        assertTrue((result.indexOf("seconds")) != -1);
    }
}
