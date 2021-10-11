// ----------

const NORMAL_JSON_FILE = "./zodiac.json";
const ELEMENTS_JSON_FILE = "./zodiac_elements.json";
const BACKGROUND_LIGHT = "hsl(61,80%,80%)";
const BACKGROUND_DARK = "hsl(80,30%,40%)";
const BACKGROUND_RANGE = [BACKGROUND_LIGHT, BACKGROUND_DARK];

const GREEN_PARTY = "Green";
const LIBERAL_PARTY = "Liberal";
const NDP_PARTY = "NDP";
const PC_PARTY = "PC";
const UNKNOWN_PARTY = "Unknown";

const NUM_SIBLINGS_FOR_SMALL_TEXT = 4;
const NUM_CHARS_FOR_TINY_TEXT = 14;

// ----------

function getFillColor(d) {
  let result = null;
  if (d.children) {
    if (d.data.name === UNKNOWN_PARTY) {
      result = d3.color("white");
    } else {
      let color = d3.scaleLinear().domain([-1, 5]).range(BACKGROUND_RANGE).interpolate(d3.interpolateHcl);
      result = color(d.depth);
    }
  } else {
    let party = d.data.party;
    // colours sampled from party websites
    if (party === GREEN_PARTY) {
      // x40, x9D, x4A
      result = d3.rgb(64, 157, 74);
    } else if (party === LIBERAL_PARTY) {
      // E0, 1F, 2B
      result = d3.rgb(224, 31, 43);
    } else if (party === NDP_PARTY) {
      // F3, 82, 30
      result = d3.rgb(243, 50, 48);
    } else if (party === PC_PARTY) {
      // 2E, 85, C4
      result = d3.rgb(46, 133, 196);
    }
  }
  return result;
}

function hasManyChildren(d) {
  let result = false;
  let isLeaf = d.data.children == null;
  if (isLeaf && d.parent && d.parent.data && d.parent.data.children) {
    let numNodes = d.parent.data.children.length;
    result = numNodes >= NUM_SIBLINGS_FOR_SMALL_TEXT;
  }
  return result;
}

function getTextClass(d) {
  let result = "label";

  if (hasManyChildren(d)) {
    if (d.data.name && d.data.name.length >= NUM_CHARS_FOR_TINY_TEXT) {
      result = "label-tiny";
    } else {
      result = "label-small";
    }
  }

  return result;
}

// ----------

function updateNormalMode() {
  drawCircle(NORMAL_JSON_FILE);
}

function updateElementsMode() {
  drawCircle(ELEMENTS_JSON_FILE);
}

function drawCircle(jsonFile) {
  let svg = d3.select("#known");

  let pack = d3
    .pack()
    .size([diameter - margin, diameter - margin])
    .padding(2);

  d3.json(jsonFile, function (error, root) {
    root = d3
      .hierarchy(root);

    let nodes = pack(root).descendants();

    let circle = g
      .selectAll("circle")
      .data(nodes)
      .enter()
      .append("circle")

      .attr("class", function (d) {
        return d.parent ? (d.children ? "node" : "node node--leaf") : "node node--root";
      })
      .style("fill", getFillColor)
      .on("click", function (d) {
        if (focus !== d) zoom(d), d3.event.stopPropagation();
      });

    let text = g
      .selectAll("text")
      .data(nodes)
      .enter()
      .append("text")
      .attr("class", getTextClass)
      .style("fill-opacity", function (d) {
        return d.parent === root ? 1 : 0;
      })
      .style("display", function (d) {
        return d.parent === root ? "inline" : "none";
      })
      .text(function (d) {
        return d.data.name;
      });

    let node = g.selectAll("circle,text");

    svg.style("background", color(-1)).on("click", function () {
      zoom(root);
    });

    zoomTo([root.x, root.y, root.r * 2 + margin]);

    function zoom(d) {
      focus = d;

      let transition = d3
        .transition()
        .duration(d3.event.altKey ? 7500 : 750)
        .tween("zoom", function (d) {
          let i = d3.interpolateZoom(view, [focus.x, focus.y, focus.r * 2 + margin]);
          return function (t) {
            zoomTo(i(t));
          };
        });

      transition
        .selectAll("text")
        .filter(function (d) {
          return d.parent === focus || this.style.display === "inline";
        })
        .style("fill-opacity", function (d) {
          return d.parent === focus ? 1 : 0;
        })
        .on("start", function (d) {
          if (d.parent === focus) this.style.display = "inline";
        })
        .on("end", function (d) {
          if (d.parent !== focus) this.style.display = "none";
        });
    }

    function zoomTo(v) {
      const k = diameter / v[2];
      view = v;
      node.attr("transform", function (d) {
        return "translate(" + (d.x - v[0]) * k + "," + (d.y - v[1]) * k + ")";
      });
      circle.attr("r", function (d) {
        return d.r * k;
      });
    }
  });
} // drawCircle

function modeCheckboxHandler(event) {
  const value = event.target.value;
  if (value === "normal") {
    drawCircle(NORMAL_JSON_FILE);
  } else if (value === "elements") {
    drawCircle(ELEMENTS_JSON_FILE);
  }
}

document.getElementById("checkbox-normal").addEventListener("change", modeCheckboxHandler);
document.getElementById("checkbox-elements").addEventListener("change", modeCheckboxHandler);
