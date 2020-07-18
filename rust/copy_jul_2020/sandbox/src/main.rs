
#[derive(Debug)]
struct Player<'a> {
    name: &'a str,
    cards: Vec<u32>,
}

#[derive(Debug)]
struct Bid<'a> {
    offer: u32,
    bidder_name: &'a str,
}

fn get_bids(players: Vec<Player>) -> Vec<Bid> {
    let mut bids = vec![];
    for player in players {
        let bid = Bid{offer: 10, bidder_name: player.name};
        bids.push(bid);
    }
    bids
}

fn show_bid(bid: Bid) {
    println!("TRACER bid: {:?}", bid);
}

fn go(players: Vec<Player>) {
    let bids = get_bids(players);
    for bid in bids {
        show_bid(bid);
    }
}

fn main() {
    let p1 = Player{name: "mozart", cards: vec![1,2,3]};
    let p2 = Player{name: "beethoven", cards: vec![1,2,3]};
    let players = vec![p1, p2];
    go(players);
    println!("Ready.");
}
