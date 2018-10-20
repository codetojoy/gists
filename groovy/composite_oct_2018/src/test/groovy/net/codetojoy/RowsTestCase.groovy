
package net.codetojoy

import org.junit.*
import static org.junit.Assert.*

class RowsTestCase {
    @Test
    void testCanary() {
        assertEquals(4, 2+2)
    }

    // TODO: move this
    @Test
    void testBuildRow() {
        // test
        Row result = new Row("1","2","3","4","5","6","7","8","9")

        assert 1 == result.getQuestionId()
        assert 9 == result.getAnswerScore()
    }
}
