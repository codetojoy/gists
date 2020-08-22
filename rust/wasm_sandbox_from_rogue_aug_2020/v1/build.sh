#!/bin/bash

cargo build --target wasm32-unknown-unknown

wasm-pack build

#### hack !!!!

echo "---------------------"
echo "  UGLY HACK ... (due to incorrect usage of webpack or wasm_bindgen?)"
echo "---------------------"

cd pkg

mv sandbox_bg.js sandbox_bg.js.tmp
echo "import {update_message} from '../update.js';" > tmp.txt
cat tmp.txt sandbox_bg.js.tmp > sandbox_bg.js
rm tmp.txt sandbox_bg.js.tmp

cd ..
