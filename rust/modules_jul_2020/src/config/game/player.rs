
use super::game_emit_log;
use super::super::config_emit_log;

pub fn my_player_func(s: &str) {
    println!("TRACER player s: {}", s);
}

pub fn bubble_func(s: &str) {
    config_emit_log("from bubble cp A");
    game_emit_log("from bubble cp B");
    println!("TRACER             {}", s);
}
