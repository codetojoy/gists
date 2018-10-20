
package net.codetojoy

import org.junit.*
import static org.junit.Assert.*

class PartitionerTestCase {
    def rows = []
    def partitioner = new Partitioner()

    @Test
    void testCanary() {
        assertEquals(4, 2+2)
    }

    @Test
    void testPartitionByGroup() {
        def rows = new Rows().getRows()

        // test
        List<List<Row>> results = partitioner.partitionByGroup(rows)

        assert 3 == results.size()
        assert 2 == results.get(0).size()
        assert 5 == results.get(1).size()
        assert 6 == results.get(2).size()
    }

    @Test
    void testFindRowsByTier() {
        def rows = new Rows().getRows()

        // test
        List<Row> results = partitioner.findRowsByTier(rows, 1)

        assert 8 == results.size()
    }
}
