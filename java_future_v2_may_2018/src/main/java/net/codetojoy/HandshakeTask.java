
package net.codetojoy;

import java.util.concurrent.Callable;

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
