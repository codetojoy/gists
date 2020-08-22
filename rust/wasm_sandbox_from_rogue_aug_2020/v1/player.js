import { getRandomInt } from './util';

let Player = function () {
    this.bar();
}

Player.prototype.bar = function () {
    const x = getRandomInt(100);
    console.log(`TRACER hello from Player.bar rand: ${x}`);
}

export { Player };
