
package net.codetojoy

class Row {
    static def Q_ID = "Q_ID"
    static def GROUP = "GROUP"
    static def TIER = "TIER"
    static def LEVEL = "LEVEL"
    static def Q_PREFIX = "Q_PREFIX"
    static def Q_TEXT = "Q_TEXT"
    static def A_ID = "A_ID"
    static def A_TEXT = "A_TEXT"
    static def A_SCORE = "A_SCORE"

    static def COLS = [Q_ID, GROUP, TIER, LEVEL, Q_PREFIX, Q_TEXT, A_ID, A_TEXT, A_SCORE]
    static def NUM_COLS = COLS.size()

    Map row = [:]

    int getQuestionId() { return Integer.parseInt(row[Q_ID]) }
    int getGroup() { return Integer.parseInt(row[GROUP]) }
    int getTier() { return Integer.parseInt(row[TIER]) }
    int getLevel() { return Integer.parseInt(row[LEVEL]) }
    String getPrefix() { return row[Q_PREFIX] }
    String getQuestionText() { return row[Q_TEXT] }
    int getAnswerId() { return Integer.parseInt(row[A_ID]) }
    String getAnswerText() { return row[A_TEXT] }
    int getAnswerScore() { return Integer.parseInt(row[A_SCORE]) }

    boolean hasAnswer() {
        String idField = row[A_ID]
        boolean result = (idField != null && (! idField.isEmpty()))
        return result
    }

    public Row(String... args) {
        assert NUM_COLS == args.size()
        NUM_COLS.times { i ->
            def key = "${COLS[i]}"
            row[key] = args[i]
        }
    }
}
