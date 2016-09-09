package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemLeakServlet extends HttpServlet {
    private final List<Integer[]> list = new ArrayList<Integer[]>();

  public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException
  {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    
    out.println("<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>");
    out.println("<h4>Hello World!</h4>");
    out.println("<p>com.sun.management.jmxremote : " +  System.getProperty("com.sun.management.jmxremote") + "</p>");
    out.println("</BODY></HTML>");

    for (int i = 0; i < 1000000; ++i) {
	list.add(new Integer[1024 * 1024]);
	try {
	    Thread.sleep(20);
	} catch (InterruptedException _ignore_) {
	    System.out.println("Exception: " + _ignore_.getMessage());
	    _ignore_.printStackTrace();
	}
    }

  }
}
