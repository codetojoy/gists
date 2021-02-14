
mod player;
mod strategy;
mod card;
mod hand;

fn main() {
    let who_am_i = &String::from("main");

    player::foo(who_am_i);
    strategy::strategy::select(who_am_i);
    card::card::bar(who_am_i);
    hand::deal(who_am_i);

    println!("Ready.");
}
