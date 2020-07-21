#!/bin/bash 

for i in {1..10}
do
   echo ""
done

# Rust 
cargo install wasm-pack
wasm-pack build 

# Node/web 
npm install 
node_modules/.bin/webpack
cp index.html ./dist/.

for i in {1..5}
do
   echo ""
done
echo "TRACER Rust deliverables in ~/pkg"
echo "TRACER webpack deliverables in ~/dist"
echo "TRACER exec ./run.sh and then browse to ~/dist/index.html"
echo "Ready."
