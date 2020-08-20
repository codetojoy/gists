import { Engine } from './pkg/sandbox';

const Game = {
    engine: null,
    bruttoBug: null,
    player: null,

    init: function () {
        console.log("TRACER v 08.20 09:50 init ...");

        const engine_id = getRandomInt(18);
        this.engine = new Engine(engine_id);
        console.log("TRACER engine: " + this.engine);
        this.bruttoBug = new BruttoBug(1,2);
        this.player = new Player();
        this.bruttoBug.foo();
        this.player.bar();

        document.getElementById("go-button").addEventListener("click", this.go);
    },

    go: function() {
        console.log(`TRACER before Rust call`);
        const message_id = getRandomInt(50);
        Game.engine.generate_message(message_id);
        console.log(`TRACER AFTER Rust call`);
    },
};

const BruttoBug = function (x, y) {
    BruttoBug.prototype.act = function () {
        console.log(`TRACER hello from act`);
        this.foo();
    }
}

BruttoBug.prototype.foo = function () {
    console.log(`TRACER hello from foo`);
}

// ------------

function mySleep(time_in_ms) {
  return new Promise((resolve) => setTimeout(resolve, time_in_ms));
}

function getRandomInt(n) {
    let min = Math.ceil(1);
    let max = Math.floor(n);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

// ------------

let Player = function () {
    this.bar();
}

Player.prototype.bar = function () {
    const x = getRandomInt(100);
    console.log(`TRACER hello from Player.bar rand: ${x}`);
}

Game.init();

export function update_message(message) {
    console.log(`TRACER hello from update message: ${message.message}`);
    document.getElementById("engine-id").textContent = message.engine_id;
    document.getElementById("message-id").textContent = message.message_id;
    document.getElementById("message").textContent = message.message;
}
