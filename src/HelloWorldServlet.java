package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import weblogic.logging.NonCatalogLogger;

public class HelloWorldServlet extends HttpServlet {
//    private NonCatalogLogger ncat = new NonCatalogLogger("HelloWorldServlet");

  public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException
  {
//    ncat.info("> service()");

    //DBG
/*
    System.out.println("AA:" 
    	+ weblogic.servlet.internal.ServletRequestImpl.class.getClassLoader().getClass().getName());
    System.out.println("AA:" 
    	+ weblogic.servlet.internal.ServletRequestImpl.class.getClassLoader().toString());
    	
    System.out.println("ClassLoader is " 
    	+ weblogic.kernel.T3SrvrLogger.class.getClassLoader().getClass().getName());
    System.out.println("ClassLoader is " 
    	+ weblogic.kernel.T3SrvrLogger.class.getClassLoader().toString());
    System.out.println("2: " + Thread.currentThread().getContextClassLoader().getClass().getName());
    System.out.println("2: " + Thread.currentThread().getContextClassLoader().toString());
*/
//    weblogic.kernel.T3SrvrLogger.logWarnPossibleStuckThread("DBG!!", 1234, "DUMMY", 3456, "DUMMY_STRACE");

    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    
    out.println("<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>");
    out.println("<h4>Hello World!</h4>");
    out.println("<p>com.sun.management.jmxremote : " +  System.getProperty("com.sun.management.jmxremote") + "</p>");
    out.println("</BODY></HTML>");

//    ncat.info("< service(10)");
  }
}
