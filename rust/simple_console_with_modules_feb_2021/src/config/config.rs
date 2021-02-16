
use std::vec::Vec;

use crate::player::player::Player;

pub fn get_players() -> Vec<Player> { 
    let mut players = vec![];

    players.push(Player{name: String::from("Bach"), hand: Vec::<u8>::new()});
    players.push(Player{name: String::from("Beethoven"), hand: Vec::<u8>::new()});
    players.push(Player{name: String::from("Mozart"), hand: Vec::<u8>::new()});
    players.push(Player{name: String::from("Schubert"), hand: Vec::<u8>::new()});
    players.push(Player{name: String::from("Shostakovich"), hand: Vec::<u8>::new()});

    return players;
}
