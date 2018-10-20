
package net.codetojoy

class Rows {
    List<Row> getRows() {
        List<Row> rows = []

        // Q1
        rows << new Row("100", "1", "1", "1", "Q1", "what?", "500", "Yes", "1")
        rows << new Row("100", "1", "1", "1", "Q1", "what?", "501", "No", "0")

        // Q2 -> Q2.1
        rows << new Row("200", "2", "1", "1", "Q2", "who?", "502", "Red", "0")
        rows << new Row("200", "2", "1", "1", "Q2", "who?", "503", "Blue", "1")
        rows << new Row("200", "2", "1", "1", "Q2", "who?", "504", "Green", "0")
            rows << new Row("201", "2", "2", "1", "Q2.1", "why?", "500", "Yes", "1")
            rows << new Row("201", "2", "2", "1", "Q2.1", "why?", "501", "No", "0")

        // Q3 -> Q3.1 -> Q3.2
        rows << new Row("300", "3", "1", "1", "Q3", "when?", "502", "Red", "0")
        rows << new Row("300", "3", "1", "1", "Q3", "when?", "503", "Blue", "1")
        rows << new Row("300", "3", "1", "1", "Q3", "when?", "504", "Green", "0")
            rows << new Row("301", "3", "2", "1", "Q3.1", "how?", "", "", "")
                rows << new Row("302", "3", "2", "2", "Q3.1.1", "which?", "500", "Yes", "1")
                rows << new Row("302", "3", "2", "2", "Q3.1.1", "which?", "501", "No", "0")

        return rows
    }
}
