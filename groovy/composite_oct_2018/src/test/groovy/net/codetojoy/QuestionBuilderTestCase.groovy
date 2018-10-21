
package net.codetojoy

import org.junit.*
import static org.junit.Assert.*

class QuestionBuilderTestCase {
    def rows = []
    def questionBuilder = new QuestionBuilder()
    def partitioner = new Partitioner()

    @Test
    void testTransformAndCollectAnswers() {
        def rows = new Rows().getRows()

        // test
        def results = questionBuilder.transformAndCollectAnswers(rows)

        assert 6 == results.size()
    }

    @Test
    void testStitchHierarchy() {
        def rows = new Rows().getRows()
        def questions = questionBuilder.transformAndCollectAnswers(rows)
        def groups = partitioner.partitionByGroup(questions)

        // test
        def results = questionBuilder.stitchHierarchy(groups)

        assert 3 == results.size()

        assert 100 == results.get(0).id
        assert 200 == results.get(1).id
        assert 300 == results.get(2).id

        assert 0 == results.get(0).getSubQuestions().size()
        assert 1 == results.get(1).getSubQuestions().size()
        assert 1 == results.get(2).getSubQuestions().size()

        assert 201 == results.get(1).getSubQuestions().get(0).id
        assert 301 == results.get(2).getSubQuestions().get(0).id

        assert 0 == results.get(1).getSubQuestions().get(0).getSubQuestions().size()
        assert 1 == results.get(2).getSubQuestions().get(0).getSubQuestions().size()

        assert 302 == results.get(2).getSubQuestions().get(0).getSubQuestions().get(0).id
    }

    @Test
    void testStitchHierarchyForGroup() {
        def rows = new Rows().getRows()
        def questions = questionBuilder.transformAndCollectAnswers(rows)
        def groups = partitioner.partitionByGroup(questions)
        assert 3 == groups.size()
        def group = groups.get(2)

        // test
        def results = questionBuilder.stitchHierarchyForGroup(group)

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
