
use std::thread;
use std::vec::Vec;

use rayon::prelude::*;

#[derive(Debug)]
struct Info {
    x: u32,
    y: u32,
}

// log with thread id prefix
pub fn t_log(s: &str) {
    println!("TRACER {:?} -- {}", thread::current().id(), s);
}

fn go() {
    (0..100).into_par_iter().for_each(|x|
        t_log(&format!("{:?}", x))
    );
}

fn go2() {
    let v = vec![10,20,30,40,50,60];
    v.into_par_iter().for_each(|x|
        t_log(&format!("{:?}", x))
    );
}

fn go3() {
    let mut list = vec![];
    list.push(Info{x: 10, y: 11});
    list.push(Info{x: 20, y: 12});
    list.push(Info{x: 30, y: 13});
    list.push(Info{x: 40, y: 14});
    list.push(Info{x: 50, y: 15});

    list.into_par_iter().for_each(|x|
        t_log(&format!("{:?}", x))
    );
}

fn main() {
    let which = 3;

    match which {
        1 => go(),
        2 => go2(),
        3 => go3(),
        _ => go(),
    }
    println!("Ready.");
}
