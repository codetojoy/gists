
package net.codetojoy

class Builder {
    def currentQuestion
    def parentQuestion

    List<Question> safeAdd(List<Question> questions, Question question) {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        questions.add(question);
        return questions;
    }

    /*
    List<List<Map>> partitionByGroup(List<Row> rows) {
        List<List<Map>> groups = new ArrayList<ArrayList<Map>>();

        int currentGroup = -1;
        List<Question> currentQuestions = new ArrayList<>()

        def iterator = rows.iterator()

        while (iterator.hasNext()) {
            def row = iterator.next()
            def thisGroup = Integer.parseInt(row[Rows.GROUP]))

            if (thisGroup == group) {
                currentQuestions.add(row)
            } else {
            }
        }

        return groups;
    }
    */

    List<Question> build(List<Row> rows) {
        List<Question> questions = []

        def state = new State()

        def iterator = rows.iterator()

        while (iterator.hasNext()) {
            Row row = iterator.next()

            def thisState = state.getState(row)

            if (thisState == State.NEW_GROUP_NO_ANSWER) {
                currentQuestion = parseQuestion(row, false)
                questions << currentQuestion
            } else if (thisState == State.NEW_GROUP_WITH_ANSWER) {
                currentQuestion = parseQuestion(row, true)
                questions << currentQuestion
            } else if (thisState == State.ANSWER) {
                Answer answer = parseAnswer(row)
                currentQuestion.answers << answer
            }
        }

        return questions
    }

    Question parseQuestion(Row row, def hasAnswer) {
        Question question = new Question()

        question.setId(row.getQuestionId())
        question.setGroup(row.getGroup())
        question.setTier(row.getTier())
        question.setLevel(row.getLevel())

        question.setPrefix(row.getPrefix())
        question.setText(row.getQuestionText())

        if (hasAnswer) {
            Answer answer = parseAnswer(row)
            question.answers << answer
        }

        return question
    }

    Answer parseAnswer(Row row) {
        Answer answer = new Answer()

        answer.setId(row.getAnswerId())
        answer.setText(row.getAnswerText())
        answer.setScore(row.getAnswerScore())

        return answer
    }
}
