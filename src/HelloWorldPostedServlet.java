package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldPostedServlet extends HttpServlet {

  public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException
  {
    String val01 = req.getParameter("VAL01");

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    out.println("<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>");
    out.println("<h4>Hello World!</h4>");
    out.println("<p>com.sun.management.jmxremote : " +  System.getProperty("com.sun.management.jmxremote") + "</p>");
    out.println("VAL01:" + val01);
    out.println("</BODY></HTML>");

  }
}
