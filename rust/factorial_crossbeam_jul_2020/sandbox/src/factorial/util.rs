
use std::thread;

// log with thread id prefix
pub fn t_log(s: &str) {
    println!("TRACER {:?} -- {}", thread::current().id(), s);
}
