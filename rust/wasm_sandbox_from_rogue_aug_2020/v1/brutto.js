const BruttoBug = function (x, y) {
    BruttoBug.prototype.act = function () {
        console.log(`TRACER hello from act`);
        this.foo();
    }
}

BruttoBug.prototype.foo = function () {
    console.log(`TRACER hello from foo`);
}

export { BruttoBug };
