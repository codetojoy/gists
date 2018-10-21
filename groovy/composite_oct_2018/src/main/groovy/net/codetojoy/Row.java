
package net.codetojoy;

import java.util.*;

class Row {
    static final String Q_ID = "Q_ID";
    static final String GROUP = "GROUP";
    static final String TIER = "TIER";
    static final String LEVEL = "LEVEL";
    static final String Q_PREFIX = "Q_PREFIX";
    static final String Q_TEXT = "Q_TEXT";
    static final String A_ID = "A_ID";
    static final String A_TEXT = "A_TEXT";
    static final String A_SCORE = "A_SCORE";

    static List<String> COLS = new ArrayList<>();
    static int NUM_COLS;

    static {
        COLS.add(Q_ID);
        COLS.add(GROUP);
        COLS.add(TIER);
        COLS.add(LEVEL);
        COLS.add(Q_PREFIX);
        COLS.add(Q_TEXT);
        COLS.add(A_ID);
        COLS.add(A_TEXT);
        COLS.add(A_SCORE);
        
        NUM_COLS = COLS.size();
    }

    Map<String,String> row = new HashMap<>();

    int getQuestionId() { return Integer.parseInt(row.get(Q_ID)); }
    int getGroup() { return Integer.parseInt(row.get(GROUP)); }
    int getTier() { return Integer.parseInt(row.get(TIER)); }
    int getLevel() { return Integer.parseInt(row.get(LEVEL)); }
    String getPrefix() { return row.get(Q_PREFIX); }
    String getQuestionText() { return row.get(Q_TEXT); }
    int getAnswerId() { return Integer.parseInt(row.get(A_ID)); }
    String getAnswerText() { return row.get(A_TEXT); }
    int getAnswerScore() { return Integer.parseInt(row.get(A_SCORE)); }

    boolean hasAnswer() {
        String idField = row.get(A_ID);
        boolean result = (idField != null && (! idField.isEmpty()));
        return result;
    }

    public Row(String... args) {
        if (args.length != NUM_COLS) {
            throw new IllegalArgumentException("internal error");
        }

        for (int i = 0; i < NUM_COLS; i++) {
            row.put(COLS.get(i), args[i]);
        }
    }
}
