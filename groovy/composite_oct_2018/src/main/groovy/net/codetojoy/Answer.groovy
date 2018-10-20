
package net.codetojoy;

class Answer {
    int id
    String text
    int score

    public String toString() {
        StringBuilder builder = new StringBuilder()

        builder.append(" id: " + id)
        builder.append(" text: " + text)
        builder.append(" score: " + score)

        return builder.toString()
    }
}
