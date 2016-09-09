package examples.servlets;
/*
 * @(#)SessionServlet.java	1.21 97/05/22
 * 
 * Copyright (c) 1996-1997 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * $BK\%=%U%H%&%'%"$O!"JF9q(B Sun Microsystems, Inc. $B$NHkL)>pJs(B
 * $B!J0J2<!VHkL)>pJs!W$H$$$&!K$G9=@.$5$l$^$9!#$*5RMM$O!"HkL)(B
 * $B>pJs$rBh;0<T$K3+<($7$F$O$J$i$:!"$^$?$*5RMM$,%5%s$HDy7k(B
 * $B$5$l$?;HMQ5vBz7@Ls$N>r9`$K4p$E$$$F$N$_!"$=$l$i$r;HMQ$9$k(B
 * $B$b$N$H$7$^$9!#(B
 * 
 * $B%5%s$OK\%=%U%H%&%'%"$NE,9g@-$K$D$$$F!">&IJ@-!"FCDjL\E*$X$N(B
 * $BE,9g@-!"$*$h$SBh;0<T$N8"Mx$KBP$9$kHs?/32$NL[<($NJ]>Z$r4^$_(B
 * $B$=$l$K8BDj$5$l$J$$!"L@<(E*$^$?$OL[<(E*$J!"$$$+$J$kI=L@$b(B
 * $BJ]>Z$b9T$$$^$;$s!#%5%s$O!"K\%=%U%H%&%'%"$^$?$O$=$NGI@8J*$N(B
 * $B;HMQ!"2~JQ$^$?$OHRI[$K5/0x$7$F$*5RMM$,LX$C$?$$$+$J$kB;32$K(B
 * $B$D$$$F$b!"@UG$$rIi$$$^$;$s!#(B
 * 
 * $BCx:n8"(B $BBh(B 1.0 $BHG(B
 */


//import examples.utils.common.ExampleUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import weblogic.management.MBeanHome;

/**
 * 
 * $B$3$l$O(B HTTP $B%5!<%V%l%C%H$N4JC1$J%5%s%W%k$G!"%/%i%$%"%s%H$,(B
 * $B$3$N%5!<%V%l%C%H$K%"%/%;%9$7$?2s?t$r(B HttpSession $B%/%i%9$r(B
 * $B;H$C$FDI@W$9$k$b$N$G$9!#%;%C%7%g%s(B $B%H%i%C%-%s%0$rMxMQ$9$k(B
 * $B$3$H$G!"%/%i%$%"%s%H(B $B%V%i%&%6$,%/%C%-!<$r%5%]!<%H$7$J$$>l9g(B
 * $B$G$b!"%/%i%$%"%s%H$rDI@W$7$?$j%/%i%$%"%s%H8GM-$N%G!<%?$rJ]B8(B
 * $B$9$k$3$H$,$G$-$^$9!#$3$l$r<B8=$9$k$K$O!"%H%i%C%-%s%0(B ID $B$r(B
 * $B%3!<%I2=$7$F!"%/%i%$%"%s%H$KJV$9(B HTML $B%Z!<%8$N%O%$%Q!<%j%s%/$N(B 
 * URL $B$KF~$l$^$9!#(B
 *
 * <p><h3>$B%5%s%W%k$N%S%k%I(B</h3>  
 * <ol>
 * <li> $B?7$7$$%3%^%s%I(B $B%7%'%k$r3+$-$^$9!#(B
 * <p><li>$B!V(B<a href=../examples.html#environment>$B%5%s%W%k$N%S%k%I$*$h$S(B
 * $B<B9TMQ4D6-$N%;%C%H%"%C%W(B</a>$B!W$G@bL@$5$l$F$$$k$H$*$j$K!"$3$N3+H/%7%'%k$r(B
 * $B%;%C%H%"%C%W$7$^$9!#(B
 * <p>
 * <li>$B%5%s%W%k(B $B%G%#%l%/%H%j$K0\F0$7$^$9(B:
 * <pre> prompt&gt;<b> cd %SAMPLES_HOME%\server\examples\src\examples\servlets</b></pre>
 * <p>
 * <li>$B0J2<$N$h$&$K!"(Bant $B$r;H$C$F$3$N%5!<%V%l%C%H$r%S%k%I$7$^$9!#(B
 * <pre>  prompt&gt;<b> ant SessionServlet</b></pre>
 * 
 * 
 * <p> <li><a href=../examples.html>$B%5%s%W%kMQ%3%s%U%#%0%l!<%7%g%s(B</a> $B$r;H$C$F(B
 * WebLogic Server $B$r5/F0$7$^$9!#(B<p> 
 *
 * </ol>
 * <p><h3>$B%5!<%P$N%3%s%U%#%0%l!<%7%g%s(B</h3>
 * <ol>
 * <li><font face="Courier New" size=-1>examplesWebApp</font> $B$,(B <a href=../examples.html#webApp>$B%5!<%P>e$K%G%W%m%$(B</a>$B$5$l$F$$$k$3$H$r3NG'$7$F$/$@$5$$!#(B
 * 
 * <p><li>$B%V%i%&%6$G%/%C%-!<$rL58z$K$7$?$^$^!"$3$N%5%s%W%k$r%F%9%H(B
 * $B$7$?$$>l9g$K$O!"(BURL $B=q$-49$($K$h$k%;%C%7%g%s(B $B%H%i%C%-%s%0$r(B 
 * <font face="Courier New" size=-1>examplesWebApp</font> $B$KI,$:(B
 * $B%3%s%U%#%0%l!<%7%g%s$7$F$/$@$5$$!#%G%U%)%k%H$G$O!"(BURL $B=q$-49$($O(B
 * $BL58z$K$J$C$F$$$^$9!#(B
 *
 * </ol>
 * <p><h3>$B%5%s%W%k$N<B9T(B</h3>
 * <ol>
 * <li>Web $B%V%i%&%6$r;H$C$F!"0J2<$N(B URL $B$r%m!<%I$7$^$9!#(B
 * 
 * <pre><b>http://localhost:7001/examplesWebApp/SessionServlet</b></pre>
 * <p><li>$B%V%i%&%6$G%/%C%-!<$rL58z$K$7$F$+$i!"%5%s%W%kFb$N%j%s%/$r(B
 * $B%/%j%C%/$7$F(B URL $B=q$-49$($,I,$:;H$o$l$k$h$&$K$9$k$H!":#EY$O(B
 * $B%V%i%&%6$N(B URL $B%"%I%l%9(B $B%P!<$NI=<($K%;%C%7%g%s(B ID $B$,4^$^$l$F$$$k(B
 * $B$N$,$o$+$j$^$9!#(B

 * </ol>
 * <h3>$BJdB-;v9`(B</h3>
 *
 * HTTP $B%5!<%V%l%C%H$N>\:Y$K$D$$$F$O!"!X(B<a href="http://edocs.beasys.co.jp/e-docs/wls/docs70/servlet/index.html">
 * WebLogic HTTP $B%5!<%V%l%C%H(B $B%W%m%0%i%^!<%:(B $B%,%$%I(B</a>$B!Y$r;2>H$7$F$/$@$5$$!#(B
 * <p>
 * @author Copyright (c) 1996-1998 by WebLogic, Inc. All Rights Reserved.
 * @author Copyright (c) 1999-2003 by BEA Systems, Inc. All Rights Reserved.  */


public class SessionServlet extends HttpServlet { 
  
  public void doGet (HttpServletRequest req, HttpServletResponse res)
       throws ServletException, IOException
  {
    // $B%;%C%7%g%s(B $B%*%V%8%'%/%H$r<hF@$9$k(B
    HttpSession session = req.getSession(true);

    // $B%m!<%+%kJQ?t(B
    String serverName;
    String failoverMessage = "";
    String str = (String) session.getAttribute("simplesession.servername");
    PrintWriter out = res.getWriter();
    Integer ival;

    // $B$^$:!"%3%s%F%s%D(B $B%?%$%W$J$I$N1~Ez%X%C%@!<(B $B%U%#!<%k%I$r@_Dj$9$k(B
    res.setContentType("text/html");
    
    // $B%;%C%7%g%s$+$i%+%&%s%HCM$r<h$j=P$9(B
    ival = (Integer) session.getAttribute("sessiontest.counter");
    if (ival==null) ival = new Integer(1);
    else ival = new Integer(ival.intValue() + 1);
    
    session.setAttribute("sessiontest.counter", ival);

    out.println("<HTML><HEAD><TITLE>Session Servlet</TITLE></HEAD><BODY>");

    try {
      serverName = getServerName();
      if (str != null && str.equals(serverName)) failoverMessage = "";
      else failoverMessage = " (failing over from server" +str+ ")";
      session.setAttribute("simplesession.servername", serverName);

      // $B$=$N$"$H!"1~Ez$N%G!<%?$r=q$-9~$`(B
      out.println("You have hit this page <b>" + ival + "</b> times.<p>");
      out.println("The server currently hosting this session is <B>" +
        serverName + "</B>" + failoverMessage + "<p>");
      // $B%f!<%6$,<!$N9T$N%j%s%/$r%/%j%C%/$7$?>l9g$K$O!"(B
      // SessionServlet $B$,:F$S8F$S=P$5$l$k$,!"(B
      // $B:#EY$O(B URL $B=q$-49$($,M-8z$K$J$C$F$$$k!#(B
      out.println("Click <a href=" + res.encodeURL("SessionServlet") +
                  ">here</a>");
      out.println(" to ensure that session tracking is working even " +
                  "if cookies aren't supported.<br>");
      out.println("Note that by default URL rewriting is not enabled " +
                  "because of its expensive overhead");
      out.println("<p>");
      out.println("<h4>Request and Session Data:</h4>");
      out.println("Session ID in Request: " + req.getRequestedSessionId());
      out.println("<br>Session ID in Request from Cookie: " +
                  req.isRequestedSessionIdFromCookie());
      out.println("<br>Session ID in Request from URL: " +
                  req.isRequestedSessionIdFromURL());
      out.println("<br>Valid Session ID: " + req.isRequestedSessionIdValid());
      out.println("<h4>Session Data:</h4>");
      out.println("New Session: " + session.isNew());
      out.println("<br>Session ID: " + session.getId());
      out.println("<br>Creation Time: " + session.getCreationTime());
      out.println("<br>Last Accessed Time: " + session.getLastAccessedTime());
    } catch (Exception ex) {
      out.println("<p><b>!! Example Failed !!<br><br> The following exception occur:</b><br><br>");
      ex.printStackTrace(new PrintWriter(out));
      ex.printStackTrace();
    }
    finally {
      out.println("</BODY></HTML>");

    }
  }

  private String getServerName() throws Exception {
    String toReturn = null;

    Hashtable<String,String> h = new Hashtable<String,String>();
    h.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
    h.put(Context.SECURITY_PRINCIPAL, "weblogic");
    h.put(Context.SECURITY_CREDENTIALS, "weblogic1");
    h.put(Context.PROVIDER_URL, "t3://10.185.178.48:10305");

    Context myCtx = new InitialContext(h);
    MBeanHome mbeanHome = (MBeanHome)myCtx.lookup("weblogic.management.home.localhome");
    toReturn = mbeanHome.getMBeanServer().getServerName();
    if (toReturn == null) return "";
    else return toReturn;
  }
}
