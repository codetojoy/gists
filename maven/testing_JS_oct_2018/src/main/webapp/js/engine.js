
function calculateScore(answers, scores) {
    var result = 0;

    // TODO: check that length are the same
    for (var i = 0; i < answers.length; i++) {
        const answer = answers[i];
        const scoreRef = scores[i];
        const thisScore = Number(scoreRef[answer]);
        if (isNaN(thisScore)) {
            throw new Error('Illegal answer');
        }
        result += thisScore;
    }

    return result;
}
