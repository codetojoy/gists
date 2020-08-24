import { MyRandom } from './util';
import { BruttoBug } from './brutto';
import { Player } from './player';

export class Game {
    player: Player;
    bruttoBug: BruttoBug;

    init() {
        console.log("TRACER *** v2_ts [22-AUG 13:47] *** :: init");
        this.player = new Player();
        this.bruttoBug = new BruttoBug();
        document.getElementById("go-button").addEventListener("click", this.go);
    }

    go() {
        const message_id = new MyRandom().getRandomInt(50);
        console.log(`TRACER Game.go --> ${message_id}`);
    }
}
