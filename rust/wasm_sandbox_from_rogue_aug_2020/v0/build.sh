#!/bin/bash

cargo build --target wasm32-unknown-unknown

wasm-pack build
