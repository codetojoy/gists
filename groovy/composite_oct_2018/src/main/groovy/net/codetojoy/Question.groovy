
package net.codetojoy;

class Question {
    int id
    int group
    int tier
    int level

    String prefix
    String text

    List<Answer> answers = new ArrayList<>()
    List<Question> subQuestions = new ArrayList<>()

    public String toString() {
        StringBuilder builder = new StringBuilder()

        builder.append(" id: " + id)
        builder.append(" group: " + group)
        builder.append(" tier: " + tier)
        builder.append(" level: " + level)
        builder.append(" '" + prefix + " "+ text + "'")

        builder.append("\n answers: ")
        answers.each { answer -> builder.append(answer.toString() + " | ") }
        builder.append("\n")

        return builder.toString()
    }
}
