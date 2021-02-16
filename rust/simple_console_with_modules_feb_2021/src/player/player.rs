
use std::vec::Vec;

#[derive(Debug)]
pub struct Player {
    pub name: String,
    pub hand: Vec<u8>,
}

impl Player {
    pub fn play_round(&mut self) {
        let card = self.hand.pop().unwrap();
        println!("TRACER {} plays {}", self.name, card);
        // self.hand.retain(|x| *x != offer);
    }
}
