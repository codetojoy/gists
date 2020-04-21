<%@page import="net.codetojoy.Util" %>
<%@page import="net.codetojoy.EmployeeDAO" %>
<%@page import="java.util.*" %>

<html>
<body>

<h2>time</h2>
<p> <%= new Util().getMessage() %> </p>
<hr />

<h2>employees</h2>
<% List<String> employees = new EmployeeDAO().findEmployees(); %>

<ul>
    <% for (String employee : employees) { %>
        <li><%= employee %></li>
    <% } %>
</ul>

</body>
</html>
