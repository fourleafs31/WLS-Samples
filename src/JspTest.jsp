<%@ page import="java.io.PrintWriter"%>
<%
	PrintWriter pw = null;
	try{
		response.setContentType("text/html");
		pw = response.getWriter();
		String res = "<HTML><HEAD><TITLE>TEST</TITLE></HEAD><BODY><B>HELLO WORLD!!</B></BODY></HTML>";
		//pw.print(res);
		pw.println(res);

		//response.setContentLength(res.getBytes().length + 2);
		response.setContentLength(res.getBytes().length + 4);
	}catch(Exception e){
			e.printStackTrace();
	}
%>
