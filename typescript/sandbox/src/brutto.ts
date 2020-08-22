
export class BruttoBug {
    act() {
        console.log(`TRACER hello from BruttoBug.act`);
        this.foo();
    }

    foo() {
        console.log(`TRACER hello from BruttoBug.foo`);
    }
}
