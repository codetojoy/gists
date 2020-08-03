extern crate wasm_bindgen;
use wasm_bindgen::prelude::*;

// Import 'window.alert'
#[wasm_bindgen]
extern "C" {
    fn alert(s: &str);
}

// Export a 'hello' function
#[wasm_bindgen]
pub fn greeting(name: &str) {
    alert(&format!("TRACER: greetings from {}!", name));
}

#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        assert_eq!(2 + 2, 4);
    }
}
