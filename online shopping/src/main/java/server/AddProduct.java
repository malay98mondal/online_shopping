package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add")
public class AddProduct extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			AdminBean ab=(AdminBean)hs.getAttribute("abean");
			pw.println("page belongs to "+ab.getfName()+"<br>");
			req.getRequestDispatcher("adminpage.html").include(req, res);
			int x=new AddProductDAO().add(req);
			if(x>0)
			{
				pw.println("<br>Product Added Successfully...");
			}
		}
		else
		{
			pw.println("Session Expired...");
			req.getRequestDispatcher("homepage.html").include(req, res);
		}
	}
}
