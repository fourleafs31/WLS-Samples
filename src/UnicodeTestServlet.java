package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import weblogic.logging.NonCatalogLogger;

public class UnicodeTestServlet extends HttpServlet {
//    private NonCatalogLogger ncat = new NonCatalogLogger("HelloWorldServlet");

  public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException
  {
//    ncat.info("> service()");

    String val01 = new Character('\u585A').toString();
    String val02 = new Character('\uFA10').toString();
    System.out.println("val01 = " + val01 + ", val02 =" + val02);

    res.setContentType("text/html; charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.println("<HTML><HEAD><TITLE>Unicode Test</TITLE></HEAD><BODY>");
    out.println("<h4>Unicode Test</h4>");
    out.println("<p>val01:" + val01 + "</p>");
    out.println("<p>val02:" + val02 + "</p>");
    out.println("</BODY></HTML>");

//    ncat.info("< service(10)");
  }
}
