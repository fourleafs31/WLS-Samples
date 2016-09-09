package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class OvrwrtSessionSvlt extends HttpServlet {

	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		try{
			res.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>Overwrite session test.</TITLE></HEAD><BODY>");

			HttpSession session = req.getSession(true);
	
			SimpleBean savedSb = (SimpleBean) session.getAttribute("KEY01");
			if(savedSb != null){
				out.println("<p>Attribte KEY01 has a value: " + savedSb.toString() + "</p>");
				savedSb.setId(2);
				savedSb.setDate(new java.util.Date());
				savedSb.setComment("This is second operation for updating.");
			}else{
				out.println("<p>Attribte KEY01 don't have value.</p>");
				savedSb = new SimpleBean(new Integer(1), new java.util.Date(), "This is first time operation.");
				session.setAttribute("KEY01", savedSb);
			}

			out.println("<p>Set '" + savedSb.toString() + "' to key KEY01.</p>");
			//session.setAttribute("KEY01", savedSb);
			savedSb = (SimpleBean) session.getAttribute("KEY01");
			if(savedSb != null){
				out.println("<p>Attribte KEY01 has a value: " + savedSb.toString() + "</p>");
			}else{
				out.println("<p>Attribte KEY01 don't have value.</p>");
			}
		}catch(Exception e){
			out.println("<p><b>!! Example Failed !!<br><br> The following exception occur:</b><br><br>");
			e.printStackTrace(new PrintWriter(out));
			e.printStackTrace();
		}finally {
			out.println("</body></html>");
		}
	}
}


