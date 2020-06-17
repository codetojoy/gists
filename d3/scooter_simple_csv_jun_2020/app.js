const meetupData = {
  attendees: [],
  addItem(attendee) {
    this.attendees.push(attendee);
  },
  removeItem(index) {
    this.attendees.splice(index, 1);
  },
  log() {
    for (const [i, value] of this.attendees.entries()) {
      console.log('%d: %s', i, value);
    }
  },
  shuffle() {
    d3.shuffle(this.attendees);
  }
};

function loadData() {
  d3.csv( "attendees.csv" ).then( function( data ) {
    data.forEach(function (row) {
      console.log(`TRACER row: ${row["Name"]}`);
      meetupData.addItem(row["Name"]);
    });
    update();
  });
}

function update() {
  d3.select('ul')
    .selectAll('li')
    .data(meetupData.attendees, d => d)
    .join(
      function(enter) {
        console.log(`TRACER enter`);
        return enter.append('li')
             // .classed('added', true) // debug
             .text(d => d);
      },
      function(update) {
        console.log(`TRACER update`);
        return update;
        // debug:
        // return update.classed('updated', true);
      },
      function(exit) {
        console.log(`TRACER exit`);
        return exit.remove();
        // TODO: exit not working properly when CSS
        // debug:
        // return exit.classed('redundant', true);
      }
    );
}

function play() {
  console.log("TRACER play");
  // TODO: index should be randomized
  meetupData.removeItem(0);
  meetupData.shuffle();
  meetupData.log();
  update();
}

function shuffle() {
  console.log("TRACER shuffle");
  meetupData.shuffle();
  meetupData.log();
  update();
}

function add() {
  const newAttendee = document.getElementById("new-attendee").value;
  console.log(`TRACER add: ${newAttendee}`);
  meetupData.addItem(newAttendee);
  meetupData.log();
  update();
}

document.getElementById("play-button").addEventListener("click", play);
document.getElementById("shuffle-button").addEventListener("click", shuffle);
document.getElementById("add-button").addEventListener("click", add);
