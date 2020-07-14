
pub mod game;

pub fn my_config_func(s: &str) {
    println!("TRACER config s: {}", s);
}

fn config_emit_log(s: &str) {
    println!("TRACER CONFIG LOG: {}", s);
}
