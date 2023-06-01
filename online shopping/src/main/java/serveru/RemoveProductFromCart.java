package serveru;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/remove")
public class RemoveProductFromCart extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			new RemoveProductFromCartDAO().remove(req);
			req.getRequestDispatcher("cart").forward(req, res);
		}
		else
		{
			res.getWriter().println("Session Expired...<br>");
			req.getRequestDispatcher("homepage.html").include(req, res);
			
		}
	}

}
