<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<%@page import="net.codetojoy.web.Reader" %>

<%
String utilsBuildTimestamp = new Reader().read("autogen_utils_build_timestamp.properties");
String serviceBuildTimestamp = new Reader().read("autogen_service_build_timestamp.properties");
%>

<html>
<body>

<hr/><p>utils   : <%= utilsBuildTimestamp %></p>
<hr/><p>service : <%= serviceBuildTimestamp %></p>

</body>
</html>
