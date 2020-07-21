
use wasm_bindgen::prelude::*;

#[wasm_bindgen]
pub fn replicate(s: String, n: u32) -> String {
    let mut result = "".to_owned();

    for _i in 0..n {
        result.push_str(&s);
    }

    result
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_replicate() {
        // test
        let result = replicate("5150".to_string(), 5);

        assert_eq!(result, "51505150515051505150".to_string());
    }
}
