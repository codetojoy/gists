
package net.codetojoy;

import java.util.Date;
import java.util.concurrent.*;

public class AsyncPool {
    private final int NUM_THREADS = 3;
    private ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS); 

    public <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    } 

    public void shutdown() {
        executor.shutdownNow();
    }
}
