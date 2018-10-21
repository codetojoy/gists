
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
}
