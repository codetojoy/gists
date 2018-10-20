
package net.codetojoy;

import java.util.stream.Collectors;

public class Partitioner {
    public List<List<Row>> partitionByGroup(List<Row> rows) {
        List<List<Row>> results = new ArrayList<ArrayList<Row>>();

        Map<List<Row>> groups =  rows.stream()
                                     .collect(Collectors.groupingBy{ row -> row.getGroup() });
          //                            .values();
        List<Integer> keys = groups.keySet().sort();

        for (Integer key : keys) {
            results.add(groups.get(key));
        }

        return results;
    }
}
