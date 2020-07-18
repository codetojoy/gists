
use serde_json;
use serde::{Deserialize, Serialize};

use std::fs;
use std::env;

#[derive(Debug, Serialize, Deserialize)]
struct JsonConfiguration {
    pub num_cards: u32,
    pub num_games: u32,
    pub players: Vec<JsonPlayer>
}

#[derive(Debug, Serialize, Deserialize)]
struct JsonPlayer {
    pub name: String,
    pub strategy: String,
}

fn go_from_file(file: &str) {
    let data = fs::read_to_string(file).expect("Unable to read file");
    let json_configuration: JsonConfiguration = serde_json::from_str(&data).unwrap();

    // let json: serde_json::Value = serde_json::from_str(&data).expect("JSON was not well-formatted");

    println!("TRACER num_games: {:?}", json_configuration);
}

fn main() {
    let args: Vec<String> = env::args().collect();
    go_from_file(&args[1]);
    println!("Ready.");
}
