
use std::vec::Vec;

use crate::player::player::Player;

pub fn get_players() -> Vec<Player> { 
    let mut players = vec![];

    players.push(Player{name: String::from("Bach")});
    players.push(Player{name: String::from("Beethoven")});
    players.push(Player{name: String::from("Mozart")});
    players.push(Player{name: String::from("Schubert")});
    players.push(Player{name: String::from("Shostakovich")});

    return players;
}
