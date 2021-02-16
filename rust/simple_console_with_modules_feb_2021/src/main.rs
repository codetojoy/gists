
use std::io;
use std::process;

fn list_players() {
    println!("TRACER: todo list players here");
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
