import { MyRandom } from './util';

export class Player {
    constructor() {
        this.bar();
    }
    bar() {
        const x = new MyRandom().getRandomInt(100);
        console.log(`TRACER hello from Player.bar rand: ${x}`);
    }
}
