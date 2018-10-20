
package net.codetojoy;

import java.util.stream.Collectors;

public class Partitioner {
    public List<List<Question>> partitionQuestionsByGroup(List<Question> questions) {
        List<List<Question>> results = new ArrayList<ArrayList<Question>>();

        Map<List<Question>> groups =  questions.stream()
                                     .collect(Collectors.groupingBy{ question -> question.getGroup() });
        List<Integer> keys = groups.keySet().sort();

        for (Integer key : keys) {
            results.add(groups.get(key));
        }

        return results;
    }

    public List<List<Row>> partitionByGroup(List<Row> rows) {
        List<List<Row>> results = new ArrayList<ArrayList<Row>>();

        Map<List<Row>> groups =  rows.stream()
                                     .collect(Collectors.groupingBy{ row -> row.getGroup() });
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

    public List<Row> findRowsByTier(List<Row> rows, int tier) {
        List<Row> results = rows.stream()
                                .filter{ row -> row.getTier() == tier }
                                .collect(Collectors.toList());

        return results;
    }
}
