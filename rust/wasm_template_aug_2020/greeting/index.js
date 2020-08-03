const wasm = import('./my_template');

wasm
    .then(h => h.greeting("Mozart"))
    .catch(console.error);
