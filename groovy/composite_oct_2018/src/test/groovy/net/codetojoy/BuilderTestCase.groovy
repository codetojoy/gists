
package net.codetojoy

import org.junit.*
import static org.junit.Assert.*

class BuilderTestCase {
    def rows = []
    def builder = new Builder()
    def partitioner = new Partitioner()

    @Test
    void testCanary() {
        assertEquals(4, 2+2)
    }

    @Test
    void testTransformAndCollectAnswers() {
        def rows = new Rows().getRows()

        // test
        def results = builder.transformAndCollectAnswers(rows)

        assert 6 == results.size()
    }

    @Test
    void testStitchHierarchyForGroup() {
        def rows = new Rows().getRows()
        def questions = builder.transformAndCollectAnswers(rows)
        def groups = partitioner.partitionQuestionsByGroup(questions)
        assert 3 == groups.size()
        def group = groups.get(2)

        // test
        def results = builder.stitchHierarchyForGroup(group)

        assert 1 == results.size()
        def q = results.get(0)
        assert 300 == q.id
        assert 1 == q.getSubQuestions().size()
        def subQ = q.getSubQuestions().get(0)
        assert 301 == subQ.id
        assert 1 == subQ.getSubQuestions().size()
        assert 302 == subQ.getSubQuestions().get(0).id
    }
}
