
package net.codetojoy;

import java.util.Date;
import java.util.concurrent.*;

public class AsyncBarServiceWrapper {
    // TODO: call shutdown on the service
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    public Future<BarService> startHandshake() {
        return executor.submit(new HandshakeTask());
    }
}

class HandshakeTask implements Callable<BarService> {
    @Override
    public BarService call() throws Exception {
        BarService barService = new BarService();
        // mock work:
        System.out.println("TRACER beginning 'handshake'");
        try { Thread.sleep(10*1000); } catch (Exception ex) {}
        return barService;
    }
}
