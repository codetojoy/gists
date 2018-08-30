
package net.codetojoy;

import java.util.Date;
import java.util.concurrent.Future;

public class FooService {
    Future<BarService> barServiceFuture;
    AsyncBarServiceWrapper asyncBarServiceWrapper;

    public void initialize() {
        asyncBarServiceWrapper = new AsyncBarServiceWrapper();
        barServiceFuture = asyncBarServiceWrapper.startHandshake();          
        System.out.println("TRACER " + new Date() + " initialize");
    }

    public void invokeBarService() {
        try {
            System.out.println("TRACER " + new Date() + " invoke");
            BarService barService = barServiceFuture.get();
            System.out.println("TRACER " + new Date() + " invoke. id: " + barService.getId());
        } catch (Exception ex) {
            System.err.println("TRACER FooService caught exception. ex: " + ex.getMessage());
        } finally {
            asyncBarServiceWrapper.shutdown();
        }
    }
}
