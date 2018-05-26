
package net.codetojoy;

import java.util.Date;
import java.util.concurrent.Future;

public class FooService {
    Future<BarService> barServiceFuture;

    public void initialize() {
        AsyncBarServiceWrapper asyncBarServiceWrapper = new AsyncBarServiceWrapper();
        barServiceFuture = asyncBarServiceWrapper.startHandshake();          
        System.out.println("TRACER " + new Date() + " initialize");
    }

    // TODO: catch exception
    public void invokeBarService() throws Exception {
        System.out.println("TRACER " + new Date() + " invoke");
        BarService barService = barServiceFuture.get();
        System.out.println("TRACER " + new Date() + " invoke. id: " + barService.getId());
    }
}
