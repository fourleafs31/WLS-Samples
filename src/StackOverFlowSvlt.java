package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StackOverFlowSvlt extends HttpServlet {

  public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException
  {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    
    out.println("<HTML><HEAD><TITLE>StackOverFlowSvlt</TITLE></HEAD><BODY><h4>Do StackOverFlow</h4></BODY></HTML>");
    this.execStackOverFlow();
  }
  
  public void execStackOverFlow(){
    this.func01();
  }
  
  public void func01(){
    this.execStackOverFlow();
  }
  
}
