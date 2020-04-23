<%@page import="net.codetojoy.Util" %>
<%@page import="net.codetojoy.RESTClient" %>
<%@page import="java.util.*" %>

<html>
<body>

<h2>time</h2>
<p> <%= new Util().getMessage() %> </p>
<hr />

<pre>
<%= new RESTClient().doPostToExternalService() %>
</pre>

</body>
</html>
