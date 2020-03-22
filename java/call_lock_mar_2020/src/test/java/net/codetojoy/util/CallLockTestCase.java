
package net.codetojoy.util;

import org.junit.*;
import static org.junit.Assert.*;

class Caller {
    private CallLock callLock = new CallLock();

    void testCall(String targetPackage) {
        callLock.lock(targetPackage);
    }
}

public class CallLockTestCase {

    @Test
    public void testLock_legal() {
        String targetPackage = "net.codetojoy.util";

        // test
        new Caller().testCall(targetPackage);

        // no exception is a pass
    }
}
