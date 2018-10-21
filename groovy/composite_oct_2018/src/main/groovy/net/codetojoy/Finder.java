
package net.codetojoy;

import java.util.*;
import java.util.stream.Collectors;

public class Finder {

    public Question findQuestion(List<Question> questions, int group, int tier, int level) {
        Question result = questions.stream()
                                   .filter( q -> q.getGroup() == group &&
                                                 q.getTier() == tier &&
                                                 q.getLevel() == level
                                          )
                                   .findFirst()
                                   .orElse(null);

        return result;
    }

    public Question findParentQuestion(List<Question> questions, int group, int tier, int level) {
        Question question = null;

        if (tier == 2 && level == 1) {
            question = findQuestion(questions, group, tier - 1, level);
        } else if (tier == 2 && level == 2) {
            for (Question tier1Question : questions) {
                question = findQuestion(tier1Question.getSubQuestions(), group, tier, level  - 1);
                if (question != null) {
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("internal error");
        }

        return question;
    }
}
