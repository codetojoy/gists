
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

    private String getSpacer() {
        String result = ""

        if (tier == 2 && level == 1) {
            result = "    "
        } else if (tier == 2 && level == 2) {
            result = "        "
        }

        return result
    }

    public String toString() {
        StringBuilder builder = new StringBuilder()

        String sp = getSpacer()

        builder.append(sp + " id: " + id)
        builder.append(sp + " group: " + group)
        builder.append(sp + " tier: " + tier)
        builder.append(sp + " level: " + level)
        builder.append(sp + " '" + prefix + " "+ text + "'")

        if (! answers.isEmpty()) {
            builder.append("\n" + sp + "answers: ")
            answers.each { answer -> builder.append(answer.toString() + " | ") }
            builder.append("\n")
        }

        if (! subQuestions.isEmpty()) {
            builder.append("\n" + sp + "subQuestions: ")
            subQuestions.each { subQ -> builder.append(subQ.toString()) }
            builder.append("\n")
        }

        return builder.toString()
    }
}
