describe("demo", function() {
    describe("demo reverse", function(){
        it("canary test", function() {
            expect(3+5).toEqual(8);
        });
        
        it("demo reverse", function() {
            const s = "abc123";
            const result = myReverse(s);
            expect(result).toEqual("321cba");
        });
    });
});
