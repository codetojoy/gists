describe("demo", function() {
    describe("demo reverse", function(){
        it("canary test", function() {
            expect(3+5).toEqual(8);
        });
        it("canary test 2", function() {
            var s = "abc";
            expect(s).toEqual("abc");
        });
        it("demo reverse", function() {
            var s = "abc123";
            var result = myReverse(s);
            expect(result).toEqual("321cba");
        });
    });
});
