
mod config;

use config::my_config_func;
use config::game::my_game_func;
use config::game::player::my_player_func;
use config::game::player::bubble_func;

fn main() {
    my_config_func("from main cp 1");
    my_game_func("from main cp 2");
    my_player_func("from main cp 3");
    bubble_func("from main ---> cp 4");
    println!("Ready.");
}
