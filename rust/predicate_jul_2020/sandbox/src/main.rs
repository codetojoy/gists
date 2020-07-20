
use std::ops::Fn;

fn apply_predicate<P>(x: u32, predicate: P) -> bool where P: Fn(u32) -> bool {
    predicate(x)
}

fn my_func_1(x: u32) -> bool {
    x > 20
}

fn go() {
    println!("TRACER result: {}", apply_predicate(10, my_func_1));
    println!("TRACER result: {}", apply_predicate(30, my_func_1));
}

fn main() {
    go();
    println!("Ready.");
}
