
<%@ page import="java.util.Date" %>
<%@ page import="net.codetojoy.Utils" %>

<%
    String now = new Date().toString();
    int x = new Utils().last4Digits("61605150");
%>

<html>

<body>
<p>current time <%= now %></p>
<hr />
<p>last 4 digits <%= x  %></p>
</body>

</html>
