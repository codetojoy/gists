
use std::io;
use std::process;


fn new_game() {
    println!("TRACER new game here");
}

fn play_round() {
    println!("TRACER play round here");
}

fn process_command(mut choice: String) {
    println!("You chose: {}", choice);

    let len = choice.len();
    choice.truncate(len - 1);

    match &choice.to_lowercase()[..] {
        "n" => new_game(),
        "p" => play_round(),
        "q" => process::exit(0),
        _ => println!("unknown command"),
    }
}

fn main() {
    println!("Commands:");
    println!("New game (n):");
    println!("Play round (p):");
    println!("Quit (q)):");
    println!("");

    println!("Please input your choice:");

    let mut choice = String::new();

    io::stdin().read_line(&mut choice).expect("Failed to read line");

    process_command(choice);
}
