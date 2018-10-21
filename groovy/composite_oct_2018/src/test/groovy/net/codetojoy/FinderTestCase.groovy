
package net.codetojoy

import org.junit.*

class FinderTestCase {
    def rows = []
    def finder = new Finder()

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
        Question result = finder.findParentQuestion(questions, group, tier, level)

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
        Question result = finder.findParentQuestion(questions, group, tier, level)

        assert 'Q1A' == result.getPrefix()
    }
}
