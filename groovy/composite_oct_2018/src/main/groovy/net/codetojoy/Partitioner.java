
package net.codetojoy;

import java.util.*;
import java.util.stream.Collectors;

public class Partitioner {
    // TODO: test
    public List<List<Question>> partitionByGroup(List<Question> questions) {
        List<List<Question>> results = new ArrayList<List<Question>>();

        Map<Integer, List<Question>> groups =  questions.stream()
                                                        .collect(Collectors.groupingBy( 
                                                            question -> question.getGroup() 
                                                        ));
        List<Integer> keys = new ArrayList<Integer>(groups.keySet());
        Collections.sort(keys);

        for (Integer key : keys) {
            results.add(groups.get(key));
        }

        return results;
    }
}
