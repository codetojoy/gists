
package net.codetojoy

class Builder {
    def currentQuestion
    def parentQuestion

    def partitioner = new Partitioner()

    List<Question> transformAndCollectAnswers(List<Row> rows) {
        List<Question> questions = []

        for (Row row : rows) {
            int group = row.getGroup()
            int tier = row.getTier()
            int level = row.getLevel()
            Question question = partitioner.findQuestion(questions, group, tier, level)

            if (question == null) {
                question = parseQuestion(row)
                questions.add(question)
            } else {
                Answer answer = parseAnswer(row)
                if (answer != null) {
                    question.answers.add(answer)
                }
            }
        }

        return questions
    }

    List<Question> buildGroups(List<List<Row>> groups) {
        List<Question> questions = []

        for (List<Row> group : groups) {
            questions.add(buildGroup(group))
        }

        return questions
    }

    List<Question> buildGroup(List<Row> group) {
        List<Row> tier1Rows = new Partitioner().findRowsByTier(group, 1)
        // List<Question> questions = buildTier1(tier1Rows)



    }

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

    Question parseQuestion(Row row) {
        return parseQuestion(row, row.hasAnswer())
    }

    Question parseQuestion(Row row, boolean hasAnswer) {
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
