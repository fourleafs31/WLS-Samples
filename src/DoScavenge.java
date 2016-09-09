package test.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Scavenge
 */
public class DoScavenge extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("*** new String(new byte[1*1024*1024]");
		String tmpstr = new String(new byte[1*1024*1024]);
		tmpstr = null;
	}
}
