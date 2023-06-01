package serveru;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/addtocart")
public class AddToCart extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		
		HttpSession hs=req.getSession(false);
		
		if(hs!=null)
		{
			new AddToCartDAO().add(req);
			req.getRequestDispatcher("userview").forward(req, res);

		}
		else
		{
			res.setContentType("text/html");
			PrintWriter pw=res.getWriter();
			pw.println("Session Expired...<br>");
			req.getRequestDispatcher("homepage.html").include(req, res);
		}
	}

}
