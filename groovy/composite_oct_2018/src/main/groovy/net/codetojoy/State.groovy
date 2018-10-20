
package net.codetojoy

class State {
    int lastGroup
    int lastTier
    int lastLevel

    static final int ERROR = 0
    static final int NEW_GROUP_NO_ANSWER = 1
    static final int NEW_GROUP_WITH_ANSWER = 2
    static final int ANSWER = 3

    int getState(Row row) {
        int result = ERROR

        int thisGroup = row.getGroup()
        int thisTier = row.getTier()
        int thisLevel = row.getLevel()
        boolean hasAnswer = row.hasAnswer()

        if (thisGroup == lastGroup && thisTier == lastTier && thisLevel == lastLevel) {
            if (hasAnswer) {
                result = ANSWER
            }
        } else {
            if (hasAnswer) {
                result = NEW_GROUP_WITH_ANSWER
            } else {
                result = NEW_GROUP_NO_ANSWER
            }
        }

        lastGroup = thisGroup
        lastTier = thisTier
        lastLevel = thisLevel

        return result
    }
}
