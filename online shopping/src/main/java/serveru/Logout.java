package serveru;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/ulogout")
public class Logout extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			hs.removeAttribute("ubean");
			hs.invalidate();
			pw.println("Logged out Successfully...<br>");
		}
		else
		{
			pw.println("Session Expired...<br>");
		}
		req.getRequestDispatcher("homepage.html").include(req, res);
	}

}
