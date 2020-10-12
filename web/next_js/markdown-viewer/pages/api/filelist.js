import fs from 'fs'
import path from 'path'

let response = null;

export default (req, res) => {
    for (let i = 1; i <= 50; i++) { console.log("") }
    response = res
    findFiles()
}

// MARKDOWN: src folder
const postsDirectory = path.join(process.cwd(), 'posts')

function findFiles() {
    walk(postsDirectory, myHandler)
}

function myHandler(err, results) {
    if (err) {
        console.log(`TRACER caught error: ${err}`)
    } else {
        let obj = {log: `TRACER 5150`, files: results}
        response.status(200).json(obj)
    }
}

function walk(dir, handler) {
  var results = [];
  fs.readdir(dir, function(err, list) {
    if (err) return handler(err);
    var i = 0;
    (function next() {
      var file = list[i++];
      if (!file) return handler(null, results);
      file = path.resolve(dir, file);
      fs.stat(file, function(err, stat) {
        console.log(`TRACER walk cp 1 file: ${file}`)
        if (stat && stat.isDirectory()) {
          walk(file, function(err, res) {
            results = results.concat(res);
            next();
          });
        } else {
          results.push(file);
          next();
        }
      });
    })();
  });
}
