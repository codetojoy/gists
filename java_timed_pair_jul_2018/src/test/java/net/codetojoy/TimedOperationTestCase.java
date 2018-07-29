
package net.codetojoy;

import org.junit.*;
import static org.junit.Assert.*;

public class TimedOperationTestCase {
    @Test
    public void testCanary() {
        // test
        assertEquals(4, 2+2);
    }

    @Test
    public void testTimedResult() {
        // test
        TimedResult timedResult = new TimedOperation().execute(new Operation() {
            public Object execute() {
                return new Ping().ping(); 
            }
        });

        System.out.println("TRACER result: " + timedResult.getResult());
        System.out.println("TRACER elapsed: " + timedResult.getElapsedTime());
    }
}
