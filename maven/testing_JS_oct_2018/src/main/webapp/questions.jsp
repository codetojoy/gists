<html>
<head>
    <title>Questions</title>
    <script type="text/javascript" src="js/engine.js"></script>
</head>

<body>
<form>
  <label for="q1">Q1: text here?</label>
  <br/>
  <input type="radio" name="q1" value="yes">Yes<br>
  <input type="radio" name="q1" value="no">No<br>
  <input type="radio" name="q1" value="declined">Decline to Answer<br>

  <label for="q2">Q2: text here?</label>
  <br/>
  <input type="radio" name="q2" value="yes">Yes<br>
  <input type="radio" name="q2" value="no">No<br>
  <input type="radio" name="q2" value="declined">Decline to Answer<br>

  <label for="q3">Q3: text here?</label>
  <br/>
  <input type="radio" name="q3" value="yes">Yes<br>
  <input type="radio" name="q3" value="no">No<br>
  <input type="radio" name="q3" value="declined">Decline to Answer<br>
  <br/>

  <input type="submit" value="Submit">
</form>

<h3>Score:</h3>
<textarea id="score" rows="10" cols="80">0</textarea>
<br/>
<hr/>
<button id="calculateButton">Calculate Score</button>

<script>
document.getElementById("calculateButton").onclick = function() {myFunction()};

function getValue(targetName) {
    var result = "";
    var radios = document.getElementsByName(targetName);
    var isDone = false;
    var index = 0;

    while (!isDone && (index < radios.length)) {
        if (radios[index].checked) {
            result = radios[index].value;
            isDone = true;
        }
        index++;
    }

    return result;
}

function myFunction() {
    const answers = [];
    answers.push(getValue("q1"));
    answers.push(getValue("q2"));
    answers.push(getValue("q3"));

    const scores = [
        {"yes": 1, "no": 0, "declined": 0},
        {"yes": 0, "no": 1, "declined": 0},
        {"yes": 0, "no": 0, "declined": 1}
    ];
    const score = calculateScore(answers, scores);

    console.log(`TRACER answers: ${answers}`);
    console.log(`TRACER score: ${score} `);

    document.getElementById("score").value = "" + score;
}
</script>

</body>
</html>
