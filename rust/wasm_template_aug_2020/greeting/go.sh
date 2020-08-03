#!/bin/bash

rm -rf target dist

# builds the *.wasm module file
cargo build --target wasm32-unknown-unknown

# builds the JS bridge
wasm-bindgen target/wasm32-unknown-unknown/debug/my_template.wasm --out-dir . 

# see package.json
npm run build

# browse to http://localhost:8080/index.html
npm run serve
