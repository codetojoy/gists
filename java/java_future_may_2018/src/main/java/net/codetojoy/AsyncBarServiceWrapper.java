
package net.codetojoy;

import java.util.Date;
import java.util.concurrent.*;

public class AsyncBarServiceWrapper {
    private ExecutorService executor = Executors.newSingleThreadExecutor(); 

    public Future<BarService> startHandshake() {
        return executor.submit(new HandshakeTask());
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}

class HandshakeTask implements Callable<BarService> {
    @Override
    public BarService call() throws Exception {
        BarService barService = new BarService();
        // mock work:
        System.out.println("TRACER handshake: start");
        int N = 10;
        for (int i = 0; i < N; i++) {
            System.out.println("TRACER handsake. working i: " + i);
            try { Thread.sleep(1*1000); } catch (Exception ex) { }
        }
        return barService;
    }
}
