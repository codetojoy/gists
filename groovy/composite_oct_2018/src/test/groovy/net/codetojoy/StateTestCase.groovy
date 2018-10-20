
package net.codetojoy

import org.junit.*
import static org.junit.Assert.*

class StateTestCase {
    def state = new State()

    @Test
    void testCanary() {
        assertEquals(4, 2+2)
    }

    @Test
    void testBuild_SimpleOne() {
        /*
        // Q1
        rows << r.buildRow("100", "1", "1", "1", "Q1", "what?", "500", "Yes", "1")

        // test
        def results = builder.build(rows)

        assert 1 == results.size()
        */
    }
}
