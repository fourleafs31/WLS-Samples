<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*,"%>

<html>

<head>
<title>WebLogic Server Examples</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body >
<h1>Sleep Servlet</h1>
<%
	System.out.println("[sleep.jsp] Start sleep ...");
	try{
		Thread.sleep(10*1000);
		System.out.println("[sleep.jsp] ... waikup!!");
	}catch(Exception e){
		;
	}
%>
</body>
</html>
