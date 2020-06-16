// TODO: data should come from CSV ?
const meetupData = {
  attendees: ['Dustin Sparks', 'Evan Porter', 'Matt Duffy', 'Mike Berger', 'Nolan Phillips'],
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
  // https://stackoverflow.com/a/2450976/12704
  shuffle() {
      const array = this.attendees;
      var currentIndex = array.length;

      // While there remain elements to shuffle...
      while (0 !== currentIndex) {

        // Pick a remaining element...
        const randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;

        // And swap it with the current element.
        const temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
      }
  }
};

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
