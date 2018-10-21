
package net.codetojoy;

import java.util.*;

class Question {
    private int id;
    private int group;
    private int tier;
    private int level;

    private String prefix;
    private String text;

    private List<Answer> answers = new ArrayList<>();
    private List<Question> subQuestions = new ArrayList<>();

    // getters, setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getGroup() { return group; }
    public void setGroup(int group) { this.group = group; }

    public int getTier() { return tier; }
    public void setTier(int tier) { this.tier = tier; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }

    public List<Question> getSubQuestions() { return subQuestions; }
    public void setSubQuestions(List<Question> subQuestions) { this.subQuestions = subQuestions; }

    private String getSpacer() {
        String result = "";

        if (tier == 2 && level == 1) {
            result = "    ";
        } else if (tier == 2 && level == 2) {
            result = "        ";
        }

        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        String sp = getSpacer();

        builder.append(sp + " id: " + id);
        builder.append(sp + " group: " + group);
        builder.append(sp + " tier: " + tier);
        builder.append(sp + " level: " + level);
        builder.append(sp + " '" + prefix + " "+ text + "'");

        if (! answers.isEmpty()) {
            builder.append("\n" + sp + "answers: ");
            for (Answer a : answers) {
                builder.append(a.toString() + " | ");
            }
            builder.append("\n");
        }

        if (! subQuestions.isEmpty()) {
            builder.append("\n" + sp + "subQuestions: ");
            for (Question subQ : subQuestions) {
                builder.append(subQ.toString());
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
