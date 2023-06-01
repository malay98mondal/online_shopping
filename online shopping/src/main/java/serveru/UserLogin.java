package serveru;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ulog")
public class UserLogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		UserBean ub=new UserLoginDAO().login(req);
		if(ub!=null)
		{
			HttpSession hs=req.getSession();
			hs.setAttribute("ubean",ub);
			pw.println("User: "+ub.getfName()+"<br><hr>");
			req.getRequestDispatcher("userpage.html").include(req, res);
		}
		else
		{
			pw.println("Incorrect UserId or Password...<br>");
			req.getRequestDispatcher("userlogin.html").include(req, res);
		}
	}

}
