describe("engine", function() {
    describe("calculateScore", function(){
        it("canary test", function() {
            expect(3+5).toEqual(8);
        });
        it("no answers or scores", function() {
            const answers = [];
            const scores = [];
            const result = calculateScore(answers, scores);
            expect(result).toEqual(0);
        });
        it("answers, scores, full score", function() {
            const answers = ["yes", "no", "decline"];
            const scores = [
                {"yes": 1, "no": 0, "decline": 0},
                {"yes": 0, "no": 1, "decline": 0},
                {"yes": 0, "no": 0, "decline": 1}
            ];
            const result = calculateScore(answers, scores);
            expect(result).toEqual(3);
        });
        it("answers, scores, no score", function() {
            const answers = ["no", "decline", "no"];
            const scores = [
                {"yes": 1, "no": 0, "decline": 0},
                {"yes": 0, "no": 1, "decline": 0},
                {"yes": 0, "no": 0, "decline": 1}
            ];
            const result = calculateScore(answers, scores);
            expect(result).toEqual(0);
        });
        it("illegal answer", function() {
            const answers = ["bogus"];
            const scores = [
                {"yes": 1, "no": 0, "decline": 0}
            ];
            expect(() => calculateScore(answers, scores))
                .toThrow(new Error('Illegal answer'));
        });
    });
});
