
package net.codetojoy;

import java.util.*;

class QuestionBuilder {

    List<Question> build(List<Row> rows) {
        List<Question> intermediateQuestions = transformAndCollectAnswers(rows);
        List<List<Question>> groups = new Partitioner().partitionByGroup(intermediateQuestions);
        List<Question> questions = stitchHierarchy(groups);

        return questions;
    }

    List<Question> stitchHierarchy(List<List<Question>> groups) {
        List<Question> questions = new ArrayList<>();

        for (List<Question> group : groups) {
            List<Question> innerQuestions = stitchHierarchyForGroup(group);
            questions.addAll(innerQuestions);
        }

        return questions;
    }

    List<Question> stitchHierarchyForGroup(List<Question> questions) {
        List<Question> results = new ArrayList<>();

        for (Question question : questions) {
            int group = question.getGroup();
            int tier = question.getTier();
            int level = question.getLevel();

            if (tier == 1) {
                results.add(question);
            } else {
                Question parentQuestion = new Finder().findParentQuestion(results, group, tier, level);
                parentQuestion.getSubQuestions().add(question);
            }
        }

        return results;
    }

    List<Question> transformAndCollectAnswers(List<Row> rows) {
        List<Question> questions = new ArrayList<>();

        for (Row row : rows) {
            questions = transformAndCollectAnswers(questions, row);
        }

        return questions;
    }

    protected List<Question> transformAndCollectAnswers(List<Question> questions, Row row) {
        int group = row.getGroup();
        int tier = row.getTier();
        int level = row.getLevel();
        Question question = new Finder().findQuestion(questions, group, tier, level);

        if (question == null) {
            question = parseQuestion(row);
            questions.add(question);
        } else {
            Answer answer = parseAnswer(row);
            if (answer != null) {
                question.getAnswers().add(answer);
            }
        }

        return questions;
    }

    protected Question parseQuestion(Row row) {
        Question question = new Question();

        question.setId(row.getQuestionId());
        question.setGroup(row.getGroup());
        question.setTier(row.getTier());
        question.setLevel(row.getLevel());

        question.setPrefix(row.getPrefix());
        question.setText(row.getQuestionText());

        if (row.hasAnswer()) {
            Answer answer = parseAnswer(row);
            question.getAnswers().add(answer);
        }

        return question;
    }

    protected Answer parseAnswer(Row row) {
        Answer answer = new Answer();

        answer.setId(row.getAnswerId());
        answer.setText(row.getAnswerText());
        answer.setScore(row.getAnswerScore());

        return answer;
    }
}
