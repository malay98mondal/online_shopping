package serveru;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/purchase")
public class Purchase extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			UserBean ub=(UserBean)hs.getAttribute("ubean");
			pw.println("User: "+ub.getfName()+"<br><hr>");
			req.getRequestDispatcher("userpage.html").include(req, res);
			int x=new PurchaseDAO().purchase(req);
			if(x>0)
			{
				
				pw.println("<br>Your Order is Placed...");
			}
			else
			{
				pw.println("<br>Transaction failed...");
			}
		}
		else
		{
			pw.println("Session Expired...<br>");
			req.getRequestDispatcher("homepage.html").include(req, res);
		}
	}

}
