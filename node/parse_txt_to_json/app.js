const express = require("express");
const request = require("request");

const app = express();
const port = 3000;

app.use(express.static("public"));

app.get("/", (req, res) => {
  res.send("TRACER Hello World! v 3");
});

app.get("/parse", (req, res) => {
  let s = "";
  // const linksUrl = "http://localhost:5151/api-server/links.txt";
  const linksUrl = "http://localhost:3000/links.txt";
  request(linksUrl, function (error, response, body) {
    let json = JSON.parse(`{"categories": []}`);

    if (!error && response.statusCode == 200) {
      const allLines = body.split(/\r\n|\n/);
      allLines.forEach((line) => {
        const trimLine = line.trim();
        if (trimLine !== "") {
          const isCategory = !trimLine.includes(",");
          if (isCategory) {
            const category = JSON.parse(`{"name": "${trimLine}", "links": []}`);
            json["categories"].push(category);
          } else {
            const tokens = trimLine.split(",");
            const linkName = tokens[0];
            const linkUrl = tokens[1];
            const categories = json["categories"];
            const category = categories[categories.length - 1];
            const links = category["links"];
            const link = JSON.parse(
              `{"name": "${linkName}", "link": "${linkUrl}"}`
            );
            links.push(link);
          }
        }
      });

      console.log(`TRACER json ${JSON.stringify(json)}`);
    } else {
      console.log(`TRACER error ${error} ${response}`);
    }
  });

  res.send(`TRACER cp 2 ${s}`);
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
