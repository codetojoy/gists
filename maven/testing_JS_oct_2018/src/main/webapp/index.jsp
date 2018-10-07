<%@ page import="net.codetojoy.Foo" %>
<%@ page import="java.util.*" %>

<html>
<head>
    <title>Foo</title>
    <script type="text/javascript" src="js/demo.js"></script>
</head>

<body>
<p> id: <%= new Foo().getId() %> </p>
<p> date: <%= new Date().toString() %> </p>
<br/>
<hr/>
<h3>function output:</h3>
<textarea id="field1" rows="20" cols="150">
[empty]
</textarea>
<br/>
<hr/>
<button id="demoButton">Call function</button>

<script>
document.getElementById("demoButton").onclick = function() {myFunction()};

function myFunction() {
    const str = document.getElementById("field1").value;
    const newStr = myReverse(str);
    console.log(`TRACER str ${str}`);
    console.log(`TRACER newStr ${newStr}`);
    document.getElementById("field1").value = newStr;
}
</script>

</body>
</html>
