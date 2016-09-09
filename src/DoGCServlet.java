package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoGCServlet extends HttpServlet {

  public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException
  {

    System.gc();
    System.out.println("## Run Full GC.");

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    out.println("<HTML><HEAD><TITLE>Run Full GC.</TITLE></HEAD><BODY>");
    out.println("<h4>Run Full GC.</h4>");
    out.println("</BODY></HTML>");

  }
}
