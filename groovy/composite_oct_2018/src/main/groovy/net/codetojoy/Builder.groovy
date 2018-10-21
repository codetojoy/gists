
package net.codetojoy

class Builder {
    def currentQuestion
    def parentQuestion

    def partitioner = new Partitioner()

    List<Question> build(List<Row> rows) {
        List<Question> intermediateQuestions = transformAndCollectAnswers(rows)
        List<List<Question>> groups = partitioner.partitionByGroup(intermediateQuestions)
        List<Question> questions = stitchHierarchy(groups)

        return questions
    }

    List<Question> stitchHierarchy(List<List<Question>> groups) {
        List<Question> questions = []

        for (List<Question> group : groups) {
            List<Question> innerQuestions = stitchHierarchyForGroup(group)
            questions.addAll(innerQuestions)
        }

        return questions
    }

    List<Question> stitchHierarchyForGroup(List<Question> questions) {
        List<Question> results = []

        for (Question question : questions) {
            int group = question.getGroup()
            int tier = question.getTier()
            int level = question.getLevel()

            if (tier == 1) {
                results.add(question)
            } else {
                Question parentQuestion = partitioner.findParentQuestion(results, group, tier, level)
                parentQuestion.getSubQuestions().add(question)
            }
        }

        return results
    }

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

    Question parseQuestion(Row row) {
        Question question = new Question()

        question.setId(row.getQuestionId())
        question.setGroup(row.getGroup())
        question.setTier(row.getTier())
        question.setLevel(row.getLevel())

        question.setPrefix(row.getPrefix())
        question.setText(row.getQuestionText())

        if (row.hasAnswer()) {
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
