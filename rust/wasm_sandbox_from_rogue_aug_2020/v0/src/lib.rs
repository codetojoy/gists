#[macro_use]
extern crate serde_derive;

extern crate wasm_bindgen;
use wasm_bindgen::prelude::*;

#[wasm_bindgen]
extern "C" {
    fn alert(s: &str);

    #[wasm_bindgen(js_namespace = console)]
    fn log(s: &str);

    #[wasm_bindgen(module = "../index")]
    fn update_message(message: JsValue);
}

#[derive(Serialize)]
pub struct Message {
    pub engine_id: i32,
    pub message_id: i32,
    pub message: String,
}

#[wasm_bindgen]
pub struct Engine {
    engine_id: i32,
}

#[wasm_bindgen]
impl Engine {
    #[wasm_bindgen(constructor)]
    pub fn new(id: i32) -> Engine {
        Engine {
            engine_id: id,
        }
    }

    pub fn generate_message(&self, message_id: i32) {
        let message = Message {
            engine_id: self.engine_id,
            message_id: message_id,
            message: "TRACER hello from Rust".to_owned(),
        };
        update_message(JsValue::from_serde(&message).unwrap());
    }
}
