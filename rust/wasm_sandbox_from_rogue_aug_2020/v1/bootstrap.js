const index = import("./index");
index.then(() => {
    console.log("Sandbox WASM loaded...");
});

/*
const util = import("./util");
util.then(() => {
    console.log("util loaded...");
});
*/
