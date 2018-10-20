package net.codetojoy;

import java.util.concurrent.TimeUnit;

public class Timer {
    private long beginInNano;
    private long thresholdInMillis;

    private static final String SECONDS_FORMAT = " %s %.3f seconds elapsed";
    private static final String MINUTES_FORMAT = " %s %.3f minutes elapsed";

    private static final long SIXTY_SECONDS_IN_MILLIS = 60 * 1000L;
    private static final long ONE_SECOND_IN_MILLIS = 1 * 1000L;
    private static final long THRESHOLD_IN_SECONDS = 2;

    public Timer() {
        beginInNano = System.nanoTime();
        thresholdInMillis = THRESHOLD_IN_SECONDS * 1000L;
    }

    public Timer(int thresholdInSeconds) {
        beginInNano = System.nanoTime();
        thresholdInMillis = thresholdInSeconds * 1000L;
    }

    public boolean isPastThreshold() {
        long elapsedInMillis = getElapsedInMillis();
        boolean result = (elapsedInMillis > thresholdInMillis);
        return result;
    }

    public String getElapsed(String prefix) {
        String result = "N/A";

        long elapsedInMillis = getElapsedInMillis();

        if (elapsedInMillis > SIXTY_SECONDS_IN_MILLIS) {
           double elapsedInMinutes = elapsedInMillis / (1.0 * SIXTY_SECONDS_IN_MILLIS);
           result = String.format(MINUTES_FORMAT, prefix, elapsedInMinutes);
        } else {
           double elapsedInSeconds = elapsedInMillis / (1.0 * ONE_SECOND_IN_MILLIS);
           result = String.format(SECONDS_FORMAT, prefix, elapsedInSeconds);
        }

        return result;
    }

    protected long getElapsedInMillis() {
        long elapsedNanoTime = System.nanoTime() - beginInNano;
        long elapsedInMillis = TimeUnit.MILLISECONDS.convert(elapsedNanoTime, TimeUnit.NANOSECONDS);
        return elapsedInMillis;
    }
}
