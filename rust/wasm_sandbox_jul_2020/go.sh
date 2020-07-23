#!/bin/bash

cargo install wasm-pack
wasm-pack build
cd www
npm install
npm run start
