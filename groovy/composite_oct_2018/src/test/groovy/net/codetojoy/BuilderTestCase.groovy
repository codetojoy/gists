
package net.codetojoy

import org.junit.*
import static org.junit.Assert.*

class BuilderTestCase {
    def rows = []
    def builder = new Builder()

    @Test
    void testCanary() {
        assertEquals(4, 2+2)
    }

    @Test
    void testBuild_NewGroupNoAnswer() {
        // Q1
        rows << new Row("100", "1", "1", "1", "Q1", "what?", "", "", "")

        // test
        def results = builder.build(rows)

        assert 1 == results.size()
        assert 100 == results.get(0).id
        assert results.get(0).answers.isEmpty()
        assert results.get(0).subQuestions.isEmpty()
    }

    @Test
    void testBuild_NewGroupWithAnswer() {
        // Q1
        rows << new Row("100", "1", "1", "1", "Q1", "what?", "500", "Yes", "1")

        // test
        def results = builder.build(rows)

        assert 1 == results.size()
        assert 100 == results.get(0).id
        assert 500 == results.get(0).answers.get(0).id
        assert results.get(0).subQuestions.isEmpty()
    }

    @Test
    void testBuild_NewGroupWithAnswers() {
        // Q1
        rows << new Row("100", "1", "1", "1", "Q1", "what?", "500", "Yes", "1")
        rows << new Row("100", "1", "1", "1", "Q1", "what?", "501", "No", "0")

        // test
        def results = builder.build(rows)

        assert 1 == results.size()
        assert 100 == results.get(0).id
        assert 500 == results.get(0).answers.get(0).id
        assert 501 == results.get(0).answers.get(1).id
        assert results.get(0).subQuestions.isEmpty()
    }
}
