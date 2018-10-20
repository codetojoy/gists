
package net.codetojoy

import org.junit.*
import static org.junit.Assert.*

class PartitionerTestCase {
    def rows = []
    def partitioner = new Partitioner()

    @Test
    void testFindParentQuestion_case1() {
        def q1 = new Question(prefix: 'Q1', group: 1, tier: 1, level: 1)
        def q1b = new Question(prefix: 'Q1A', group: 1, tier: 2, level: 1)
        q1.getSubQuestions().add(q1b)

        def questions = []
        questions << q1

        int group = 1
        int tier = 2
        int level = 1

        // test
        Question result = partitioner.findParentQuestion(questions, group, tier, level)

        assert 'Q1' == result.getPrefix()
    }

    @Test
    void testFindParentQuestion_case2() {
        def q1 = new Question(prefix: 'Q1', group: 1, tier: 1, level: 1)
        def q1A = new Question(prefix: 'Q1A', group: 1, tier: 2, level: 1)
        def q1A1 = new Question(prefix: 'Q1A.1', group: 1, tier: 2, level: 2)
        q1.getSubQuestions().add(q1A)
        q1A.getSubQuestions().add(q1A1)

        def questions = []
        questions << q1

        int group = 1
        int tier = 2
        int level = 2

        // test
        Question result = partitioner.findParentQuestion(questions, group, tier, level)

        assert 'Q1A' == result.getPrefix()
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
