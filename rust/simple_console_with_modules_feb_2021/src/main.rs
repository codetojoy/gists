
use std::io;
use std::process;

mod config;
mod dealer;
mod player;

use crate::player::player::Player;
use crate::config::config::get_players;
use crate::dealer::dealer::deal_hands;

fn list_players(players: &Vec::<Player>) {
    for player in players {
        println!("{:?}",player);
    }
}

fn new_game(players: &mut Vec::<Player>) {
    deal_hands(players);
    list_players(players);
}

fn play_round(players: &mut Vec::<Player>) {
    for player in players {
        player.play_round();
    }
}

fn exit() {
    println!("\nRoger copy: bye for now.");
    process::exit(0);
}

fn process_command(mut choice: String, players: &mut Vec::<Player>) {
    println!("You chose: {}", choice);

    let len = choice.len();
    choice.truncate(len - 1);

    match &choice.to_lowercase()[..] {
        "l" => list_players(players),
        "n" => new_game(players),
        "p" => play_round(players),
        "q" => exit(),
        _ => println!("unknown command"),
    }
}

fn main() {
    let mut players = get_players();

    loop {
        println!("\nCommands:");
        println!("List players (l):");
        println!("New game (n):");
        println!("Play round (p):");
        println!("Quit (q)):");
        println!("");

        println!("Please input your choice:");

        let mut choice = String::new();

        io::stdin().read_line(&mut choice).expect("Failed to read line");

        process_command(choice, &mut players);
    }
}
