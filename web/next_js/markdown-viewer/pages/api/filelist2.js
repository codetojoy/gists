import fs from 'fs'
import path from 'path'

export default (req, res) => {
    // for (let i = 1; i <= 50; i++) { console.log("") }
    const obj = findFiles()
    res.status(200).json(obj)
}

// MARKDOWN: src folder
const postsDirectory = path.join(process.cwd(), 'posts')
let markdownPaths = [];

function findFiles() {
    walk_for_markdown(postsDirectory)
    const nowTime = new Date().toLocaleTimeString();
    const obj = {log: `TRACER 4140 : ${nowTime}`, files: markdownPaths}
    return obj
}

function curry(fn, ...args) {
    return (..._arg) => {
        return fn(...args, ..._arg);
    }
}

// side-effect on `markdownPaths`
function walk_dir(dir, err, list) {
    if (list && list.length > 0) {
        const file = list[0];
        const newList = list.slice(1, list.length);
        const fullPath = path.resolve(dir, file);

        fs.stat(fullPath, function(err, stat) {
            if (stat && stat.isDirectory()) {
                walk_for_markdown(fullPath);
            } else {
                const isMarkdown = /^.*\.md$/.test(fullPath)
                if (isMarkdown) {
                    console.log(`TRACER walk found file: ${fullPath}`);
                    markdownPaths.push(fullPath);
                }
                walk_dir(dir, null, newList);
            }
        }); // fs.stat
    }
}

function walk_for_markdown(dir) {
  const f = curry(walk_dir, dir)
  fs.readdir(dir, f);
}
