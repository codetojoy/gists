
use std::io;
use std::process;

mod config;
mod player;

use crate::player::player::Player;
use crate::config::config::get_players;

fn list_players() {
    let player = Player{name: String::from("TEST")};
    let players = get_players();
    for player in &players {
        println!("{:?}",player);
    }
}

fn new_game() {
    println!("TRACER: todo new game here");
}

fn play_round() {
    println!("TRACER: todo play round here");
}

fn exit() {
    println!("\nRoger copy: bye for now.");
    process::exit(0);
}

fn process_command(mut choice: String) {
    println!("You chose: {}", choice);

    let len = choice.len();
    choice.truncate(len - 1);

    match &choice.to_lowercase()[..] {
        "l" => list_players(),
        "n" => new_game(),
        "p" => play_round(),
        "q" => exit(),
        _ => println!("unknown command"),
    }
}

fn main() {
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

        process_command(choice);
    }
}
