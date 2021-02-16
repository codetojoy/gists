
use std::vec::Vec;

use crate::player::player::Player;

pub fn deal_hands(players: &mut Vec::<Player>) { 
    // This is bogus, but this project is meant to explore modules.
    // This is fine for our purposes.
    let mut counter = 1;
    for player in players {
        let mut hand = vec![];
        hand.push(counter);
        counter += 1;
        hand.push(counter);
        counter += 1;
        hand.push(counter);
        counter += 1;
        player.hand = hand;
    }
}
