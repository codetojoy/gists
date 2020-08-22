import { Engine } from './pkg/sandbox';
import { getRandomInt } from './util';
import { Player } from './player';
import { BruttoBug } from './brutto';

const Game = {
    engine: null,
    bruttoBug: null,
    player: null,

    init: function () {
        console.log("TRACER *** v1 [22-AUG 13:30] *** :: init");

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

export { Game };
