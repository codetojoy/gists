
package net.codetojoy

import org.junit.*

class PartitionerTestCase {
    def rows = []
    def partitioner = new Partitioner()

    @Test
    void testCanary() {
        assert 4 == 2 + 2
    }

    @Test
    void testParitionByGroup() {
        def rows = new Rows().getRows()
        def questions = new QuestionBuilder().transformAndCollectAnswers(rows)

        // test
        def results = partitioner.partitionByGroup(questions)

        assert 3 == results.size()

        assert 1 == results.get(0).size()
        assert 2 == results.get(1).size()
        assert 3 == results.get(2).size()

        assert 1 == results.get(0).get(0).getGroup()
        assert 2 == results.get(1).get(0).getGroup()
        assert 3 == results.get(2).get(0).getGroup()
    }
}
