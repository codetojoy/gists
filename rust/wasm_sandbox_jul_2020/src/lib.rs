
use std::fmt;

mod utils;

use wasm_bindgen::prelude::*;

extern crate web_sys;

// A macro to provide `println!(..)`-style syntax for `console.log` logging.
macro_rules! log {
    ( $( $t:tt )* ) => {
        web_sys::console::log_1(&format!( $( $t )* ).into());
    }
}

#[wasm_bindgen]
extern {
    fn alert(s: &str);
}

#[wasm_bindgen]
pub fn greet(name: &str) {
    log!("TRACER hello from greet method in Rust/WASM");
    let mut s = String::new();
    s.push_str("TRACER: hi, ");
    s.push_str(name);
    alert(&s);
}
