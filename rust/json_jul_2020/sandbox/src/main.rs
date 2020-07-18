
use serde_json;

use std::fs;
use std::env;

fn go_from_string() {
let the_file = r#"{
    "FirstName": "John",
    "LastName": "Doe",
    "Age": 43,
    "Address": {
        "Street": "Downing Street 10",
        "City": "London",
        "Country": "Great Britain"
    },
    "PhoneNumbers": [
        "+44 1234567",
        "+44 2345678"
    ]
}"#;

    let json: serde_json::Value = serde_json::from_str(the_file).expect("JSON was not well-formatted");

    println!("\n\nTRACER STR full: {}", json);
    println!("TRACER STR FirstName: {}", json["FirstName"]);
}

fn go_from_file(file: &str) {
    let data = fs::read_to_string(file).expect("Unable to read file");
    let json: serde_json::Value = serde_json::from_str(&data).expect("JSON was not well-formatted");

    println!("\n\nTRACER FILE full: {}", json);
    println!("TRACER FILE FirstName: {}", json["FirstName"]);
}

fn main() {
    let args: Vec<String> = env::args().collect();
    go_from_string();
    go_from_file(&args[1]);
    println!("Ready.");
}
