describe('demo', function() {
    describe("myReverse", function () {
        it("canary test", function () {
            const result = myReverse("abc123");

            expect(result).toBeEqual("321cba");
        });
    });
});
