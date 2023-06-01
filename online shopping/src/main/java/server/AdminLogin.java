package server;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet("/alog")
public class AdminLogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		AdminBean ab=new AdminLoginDAO().login(req);
		if(ab!=null)
		{
			HttpSession hs=req.getSession();
			hs.setAttribute("abean", ab);
			pw.println("AdminId: "+ab.getAdminId()+"<br><hr>");
			req.getRequestDispatcher("adminpage.html").include(req,res);
		}
		else
		{
			pw.println("Invalid Username or Password...<br>");
			req.getRequestDispatcher("adminlogin.html").include(req,res);
		}
	}
}
