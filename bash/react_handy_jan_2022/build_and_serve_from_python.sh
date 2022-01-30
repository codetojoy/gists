#!/bin/bash

set -e

rm -rf build
npm run build
cd build
python3 -m http.server
cd ..

echo "Ready." 

