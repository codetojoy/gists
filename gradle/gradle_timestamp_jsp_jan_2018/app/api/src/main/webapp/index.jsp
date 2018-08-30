<%@page import="net.codetojoy.utils.Utils" %>
<%@page import="net.codetojoy.service.Service" %>

<html>
<body>
<hr/> <p>version : <%= new Utils().getVersion() %></p>
<hr/> <p>user    : <%= new Service().getUserName() %></p>
</body>
</html>
