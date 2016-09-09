package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoFinalizeServlet extends HttpServlet {

  public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException
  {
    System.runFinalization();
    System.out.println("## Run Finalization.");

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    out.println("<HTML><HEAD><TITLE>Run Finalization.</TITLE></HEAD><BODY>");
    out.println("<h4>Run Finalization.</h4>");
    out.println("</BODY></HTML>");
  }
}
