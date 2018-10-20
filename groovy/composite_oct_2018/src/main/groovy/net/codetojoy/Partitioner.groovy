
package net.codetojoy;

import java.util.stream.Collectors;

public class Partitioner {
    // TODO: test
    public List<List<Question>> partitionByGroup(List<Question> questions) {
        List<List<Question>> results = new ArrayList<ArrayList<Question>>();

        Map<List<Question>> groups =  questions.stream()
                                     .collect(Collectors.groupingBy{ question -> question.getGroup() });
        List<Integer> keys = groups.keySet().sort();

        for (Integer key : keys) {
            results.add(groups.get(key));
        }

        return results;
    }

    public Question findQuestion(List<Question> questions, int group, int tier, int level) {
        Question result = null;

        for (Question question : questions) {
            if (question.getGroup() == group &&
                question.getTier() == tier &&
                question.getLevel() == level) {
                result = question;
                break;
            }
        }

        return result;
    }

    public Question findParentQuestion(List<Question> questions, int group, int tier, int level) {
        Question question = null;

        if (tier == 2 && level == 1) {
            question = findQuestion(questions, group, tier - 1, level)
        } else if (tier == 2 && level == 2) {
            for (Question tier1Question : questions) {
                question = findQuestion(tier1Question.getSubQuestions(), group, tier, level  - 1)
                if (question != null) {
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("internal error")
        }

        return question;
    }

    public List<Row> findRowsByTier(List<Row> rows, int tier) {
        List<Row> results = rows.stream()
                                .filter{ row -> row.getTier() == tier }
                                .collect(Collectors.toList());

        return results;
    }
}
