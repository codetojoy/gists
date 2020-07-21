
trait Strategy {
    fn select(&self, player: &Player) -> u32;
}

fn my_apply(player: &Player, strategy: &dyn Strategy) {
    println!("TRACER result: {}", strategy.select(player));
}

struct Player {
    name: String,
    cards: Vec<u32>,
}

struct NextCard {}

impl Strategy for NextCard {
    fn select(&self, player: &Player) -> u32 { player.cards[0] }
}

struct MaxCard {}

impl Strategy for MaxCard {
    fn select(&self, player: &Player) -> u32 { *player.cards.iter().max().unwrap() }
}

struct MinCard {}

impl Strategy for MinCard {
    fn select(&self, player: &Player) -> u32 { *player.cards.iter().min().unwrap() }
}

fn go() {
    let player = Player{name: String::from("foo"), cards: vec![2,1,10,6]};
    my_apply(&player, &MaxCard{});
    my_apply(&player, &MinCard{});
    my_apply(&player, &NextCard{});
}

fn main() {
    go();
    println!("Ready");
}
