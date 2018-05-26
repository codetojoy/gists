
package net.codetojoy;

import java.util.Date;
import java.util.concurrent.Future;

public class FooService {
    Future<BarService> barServiceFuture;

    public void initialize(AsyncPool pool) {
        barServiceFuture = pool.submit(new HandshakeTask());
        System.out.println("TRACER " + new Date() + " initialize");
    }

    public void invokeBarService() {
        try {
            System.out.println("TRACER " + new Date() + " invoke");
            BarService barService = barServiceFuture.get();
            System.out.println("TRACER " + new Date() + " invoke. id: " + barService.getId());
        } catch (Exception ex) {
            System.err.println("TRACER FooService caught exception. ex: " + ex.getMessage());
        }
    }
}
