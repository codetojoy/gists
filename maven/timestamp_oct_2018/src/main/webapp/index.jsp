<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Sandbox JSP</title>
</head>

<%@ page import="java.util.Date" %>
<%@ page import="net.codetojoy.Utils" %>

<body>

<p> timestamp : <%= new Date() %> </p>
<p> is '123' Numeric : <%= new Utils().isNumeric("123") %> </p>

<hr/>

<a href="versionInfo.jsp">versionInfo</a>

</body>
</html>
