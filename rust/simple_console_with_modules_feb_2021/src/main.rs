
use std::io;

fn main() {
    println!("Commands:");

    println!("Please input your choice:");

    let mut choice = String::new();

    io::stdin().read_line(&mut choice).expect("Failed to read line");

    println!("You chose: {}", choice);
}
