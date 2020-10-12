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
    walk_for_markdown(postsDirectory, myHandler)
}

function myHandler(err, results) {
    if (err) {
        console.log(`TRACER caught error: ${err}`)
    } else {
        let obj = {log: `TRACER 5150`, files: results}
        response.status(200).json(obj)
    }
}

function walk_for_markdown(dir, handler) {
  var results = [];
  fs.readdir(dir, function(err, list) {
    if (err) return handler(err);
    var i = 0;
    (function next() {
      var file = list[i++];
      if (!file) return handler(null, results);
      file = path.resolve(dir, file);
      fs.stat(file, function(err, stat) {

        if (stat && stat.isDirectory()) {
          walk_for_markdown(file, function(err, res) {
            results = results.concat(res);
            next();
          });
        } else {
          const isMarkdown = /^.*\.md$/.test(file)
          if (isMarkdown) {
              console.log(`TRACER walk found file: ${file}`)
              results.push(file);
          }
          next();
        }
      });
    })();
  });
}
