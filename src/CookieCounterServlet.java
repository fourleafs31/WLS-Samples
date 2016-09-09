package examples.servlets;

//import examples.utils.common.ExampleUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * 
 * $B$3$N%5!<%V%l%C%H(B $B%5%s%W%k$G$O!"%/%C%-!<$N:n@.$*$h$S<hF@$NJ}K!$H!"(B
 * $B%/%C%-!<$KM-8z4|8B$r@_Dj$9$kJ}K!$r<($7$^$9!#(B
 *
 * <p> $B$3$N%5!<%V%l%C%H$O!"%5!<%P$,5/F0$7$F$+$i$9$Y$F$N(B
 * $B%/%i%$%"%s%H$+$i2?2s%"%/%;%9$5$l$?$+$rI=<($7!"$^$?!"(B
 * $B3F%/%i%$%"%s%H$+$i(B 10 $BIC0JFb$KB3$1$F%"%/%;%9$5$l$?(B
 * $B2s?t$bI=<($7$^$9!#(B
 *
 * <p><h3>$B%5%s%W%k$N%S%k%I(B</h3>  
 * <ol>
 * <li> $B?7$7$$%3%^%s%I(B $B%7%'%k$r3+$-$^$9!#(B
 * <p><li>$B!V(B<a href=../examples.html#environment>$B%5%s%W%k$N%S%k%I$*$h$S(B
 * $B<B9TMQ4D6-$N%;%C%H%"%C%W(B</a>$B!W$G@bL@$7$F$$$k$H$*$j$K!"$3$N3+H/%7%'%k$r(B
 * $B%;%C%H%"%C%W$7$^$9!#(B
 * <p>
 * <li>$B%5%s%W%k$N%G%#%l%/%H%j$K0\F0$7$^$9(B:
 * <pre> prompt&gt;<b> cd %SAMPLES_HOME%\server\examples\src\examples\servlets</b></pre>
 * <p>
 * <li>$B0J2<$N$h$&$K!"(Bant $B$r;H$C$F$3$N%5!<%V%l%C%H$r%S%k%I$7$^$9!#(B
 * <pre>  prompt&gt;<b> ant CookieCounter</b></pre>
 * 
 * 
 * <p> <li><a href=../examples.html>$B%5%s%W%kMQ%3%s%U%#%0%l!<%7%g%s(B</a> $B$r;H$C$F(B
 * WebLogic Server $B$r5/F0$7$^$9!#(B<p> 
 *
 * </ol>
 * <p><h3>$B%5!<%P$N%3%s%U%#%0%l!<%7%g%s(B</h3>
 * <dl>
 * <dd><font face="Courier New" size=-1>examplesWebApp</font> $B$,(B
 * <a href=../examples.html#webApp>$B%5!<%P>e$K%G%W%m%$(B</a>
 * $B$5$l$F$$$k$3$H$r3NG'$7$F$/$@$5$$!#(B
 * </dl>
 * <p><h3>$B%5%s%W%k$N<B9T(B</h3>
 * <ol>
 * <li>Web $B%V%i%&%6$r;H$C$F!"0J2<$N(B URL $B$r%m!<%I$7$^$9!#(B
 * 
 * <pre><b>http://localhost:7001/examplesWebApp/CookieCounter</b></pre>
 *
 * <li>$B%V%i%&%6$G:F%m!<%IMQ%\%?%s$r%/%j%C%/$7!J$^$?$O!"BgH>$N%V%i%&%6$G$O(B
 * $B!L(BControl$B!M(B+$B!L(BR$B!M$r2!$9!K!"(B $B:F%m!<%IMQ%\%?%s$r%/%j%C%/$9$k$?$S$K%F%-%9%H$N(B
 * $B$I$A$i$N9T$N?t;z$b%$%s%/%j%a%s%H$9$kMM;R$r4Q;!$7$^$9!#(B
 *
 * <p><li>10 $BIC0J>eBT$C$F$+$i!"%V%i%&%6$N:F%m!<%IMQ%\%?%s$r:F$S%/%j%C%/$7$^$9!#(B
 * 2 $B9TL\$N%F%-%9%H$,JQ2=$9$k$3$H$KCm0U$7$F$/$@$5$$!#$3$l$O!"(B10 $BIC8e$K%/%C%-!<$N(B
 * $BM-8z4|8B$,@Z$l$?$3$H$r<($7$F$$$^$9!#:F%m!<%IMQ%\%?%s$r:F$S%/%j%C%/$9$k$H!"(B
 * 2 $B9TL\$N%+%&%s%H$O$^$?(B 1 $B$+$i;O$^$j$^$9!#(B
 *
 * </ol>
 * <h3>$BCm0U(B</h3>
 * $B%V%i%&%6$G%/%C%-!<$rL58z$K$7$F$"$k>l9g$K$O!"$3$N%5!<%V%l%C%H$OF0:n$7$^$;$s!#(B
 * $BHs%/%C%-!<BP1~$N(B Web $B%V%i%&%6$r07$&@dBP3N<B$JJ}K!$K$D$$$F$O!"(B
 * <a href="SessionServlet.html">SessionServlet</a> $B%5%s%W%k$r;2>H$7$F$/$@$5$$!#(B
 * <p>
 * HP $B%W%i%C%H%U%)!<%`$G$O!"%/%C%-!<$O(B 10 $BIC$?$C$F$bM-8z4|8B$,@Z$l$J$$$3$H$,$"$j$^$9!#(B
 * </ol>
 *
 * <h3>$BJdB-;v9`(B</h3>
 *
 * $B%5!<%V%l%C%H$N>\:Y$K$D$$$F$O!"!X(B<a href="http://edocs.beasys.co.jp/e-docs/wls/docs81/servlet/index.html">
 * WebLogic HTTP $B%5!<%V%l%C%H(B $B%W%m%0%i%^!<%:(B $B%,%$%I(B</a>$B!Y$r;2>H$7$F$/$@$5$$!#(B
 * <p>
 * @author Copyright (c) 1999-2003 by BEA Systems, Inc. All Rights Reserved.
 */
public class CookieCounterServlet extends HttpServlet {
  private int pageCount = 0;

 /**
  * $B%5!<%V%l%C%H$r=i4|2=$7$^$9!#%W%m%Q%F%#!V(Binitial$B!W$rC5$7$F!"(B
  * pageCount $BJQ?t$K@_Dj$7$^$9!#(B
  */
  public void init(ServletConfig config) throws ServletException  {
    super.init(config);
    String s = getInitParameter("initial");
    if (s == null) pageCount = 0;
    else pageCount = Integer.parseInt(s);
  }

 /**
  * $B%5!<%V%l%C%H$N(B service $B%a%=%C%I$r<BAu$7$^$9!#(B
  */
  public void service(HttpServletRequest req, HttpServletResponse res)
    throws IOException
  {
    boolean cookieFound = false;
    Cookie thisCookie = null;

    // getWriter() $B$r8F$S=P$9A0$K%3%s%F%s%D(B $B%?%$%W$r@_Dj$9$kI,MW$,$"$k(B
    res.setContentType("text/html");
    // $B$3$l$G!"(BgetWriter() $B$r8F$S=P$;$k(B
    PrintWriter out = res.getWriter();

    // $B%j%/%(%9%H$+$i%/%C%-!<$r<h$j=P$7$F$_$k(B
    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
      for(int i=0; i < cookies.length; i++) {
        thisCookie = cookies[i];
        if (thisCookie.getName().equals("CookieCount")) {
          cookieFound = true;
          break;
        }
      }
    }

    if (cookieFound == false) {
      // $B?7$7$$(B Cookie $B%$%s%9%?%s%9$r:n@.$7!"$=$NM-8z4|8B$r@_Dj$9$k(B
      thisCookie = new Cookie("CookieCount", "1");
      thisCookie.setMaxAge(60*1);
      // $B$3$N?7$7$$%/%C%-!<$r1~Ez$KDI2C$9$k(B
      res.addCookie(thisCookie);
    }

    out.println("<HTML><HEAD><TITLE>Cookie Counter</TITLE></HEAD><BODY>");
    pageCount++;
    out.println("<p>");
    out.println("<font color=blue size=+1>");
    out.println("<p><br><br><br>This page has been visited " + pageCount +
                (pageCount==1?" time":" times") +
                " before.\n");

    // $B%/%i%$%"%s%H8GM-$N%+%&%s%H$N>\:Y$rI=<($9$k(B
    if (cookieFound) {
      int cookieCount = Integer.parseInt(thisCookie.getValue());
      cookieCount++;
      // $B%/%C%-!<$N?7$7$$CM$r@_Dj$7!"$=$l$r1~Ez$KDI2C$9$k(B
      thisCookie.setValue(String.valueOf(cookieCount));
      thisCookie.setMaxAge(10);
      res.addCookie(thisCookie);

      out.println("<p>You have visited this page " +
                  thisCookie.getValue() +
                  (cookieCount==1?" time":" times") +
                  " within the past 10 seconds.\n");

    } else {
      out.println("<p>Either you haven't visited this page in the last 10 seconds, "+
                  "or your browser doesn't like cookies!\n");
    }
    out.println("</BODY></HTML>");
    // printWriter $B$O%/%m!<%:$7$J$$(B
  }
}

