
use std::collections::HashMap;

trait Strategy {
    fn select(&self, player: &Player) -> u32;
}

fn my_apply<T: Strategy>(player: &Player, strategy: T) {
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

impl MaxCard {
    fn foo(&self) { println!("TRACER sanity-check from max"); }
}

impl Strategy for MaxCard {
    fn select(&self, player: &Player) -> u32 {
        self.foo();
        *player.cards.iter().max().unwrap()
    }
}

struct MinCard {}

impl Strategy for MinCard {
    fn select(&self, player: &Player) -> u32 { *player.cards.iter().min().unwrap() }
}

fn go() {
    let player = Player{name: String::from("foo"), cards: vec![2,1,10,6]};
    my_apply(&player, MaxCard{});
    my_apply(&player, MinCard{});
    my_apply(&player, NextCard{});
}

fn go2() {
    let strategies: HashMap<String, &dyn Strategy> =
        [("max_card".to_string(), &MaxCard{} as &Strategy),
         ("min_card".to_string(), &MinCard{} as &Strategy),
         ("next_card".to_string(), &NextCard{} as &Strategy),
        ].iter().cloned().collect();

    let player = Player{name: String::from("foo"), cards: vec![2,1,10,6]};
    let strings = vec!["max_card", "min_card", "next_card"];
    for s in strings {
        let strategy = strategies.get(s).unwrap();
        println!("TRACER result2: {}", strategy.select(&player));
    }
}

fn main() {
    println!("TRACER ---------------- ");
    go();
    println!("TRACER ---------------- ");
    go2();
    println!("Ready");
}
