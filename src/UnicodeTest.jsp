<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*," contentType="text/html; charset=UTF-8" %>
<!--  pageEncoding="Windows-31J" -->
<html>

<head>
<title>WebLogic Server Examples</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body >
<h1>Unicode Test JSP</h1>
<%
    String val01 = new Character('\u585A').toString();
    String val02 = new Character('\uFA10').toString();
    System.out.println("val01 = " + val01 + ", val02 =" + val02);
%>
<p>val01: <%=val01%></p>
<p>val02: <%=val02%></p>
</body>
</html>
