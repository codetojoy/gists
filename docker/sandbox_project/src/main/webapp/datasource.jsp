<%@page import="net.codetojoy.Util" %>
<%@page import="net.codetojoy.EmployeeDaoWithDataSource" %>
<%@page import="java.util.*" %>

<html>
<body>

<h2>time</h2>
<p> <%= new Util().getMessage() %> </p>
<hr />

<h2>employees from DATASOURCE</h2>
<% List<String> employees = new EmployeeDaoWithDataSource().findEmployees(); %>

<ul>
    <% for (String employee : employees) { %>
        <li><%= employee %></li>
    <% } %>
</ul>
<p>Ready.</p>
<p>build: montreal maroons</p>

</body>
</html>
