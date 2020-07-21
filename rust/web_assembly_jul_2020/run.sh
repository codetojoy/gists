#!/bin/bash 

for i in {1..10}
do
   echo ""
done

# Node/web 
cd ./dist/.
../node_modules/.bin/wasm-server 
