
### Summary

* from [this video](https://www.youtube.com/watch?v=YHJjmsw_Sx0)
    - see [here](https://github.com/engineer-man/youtube/tree/master/108) for detailed notes
* steps
    - `.clean.sh` when copied to a new folder (or else the server won't work)
    - `./build.sh`
    - `./run.sh`
    - browse to http://localhost:3000/index.html
* specific steps
    - `cargo init --name wasm_test --lib`
    - `cargo install wasm-pack`
    - `cargo wasm-pack build`
    - `npm install`
    - `$PWD/node_modules/.bin/webpack`
