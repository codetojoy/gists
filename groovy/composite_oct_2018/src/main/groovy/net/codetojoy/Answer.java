
package net.codetojoy;

class Answer {
    private int id;
    private String text;
    private int score;

    public void setId(int id) { this.id = id; }
    public void setText(String text) { this.text = text; }
    public void setScore(int score) { this.score = score; }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(" id: " + id);
        builder.append(" text: " + text);
        builder.append(" score: " + score);

        return builder.toString();
    }
}
