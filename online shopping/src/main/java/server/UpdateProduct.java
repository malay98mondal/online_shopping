package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/update")
public class UpdateProduct extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			AdminBean ab=(AdminBean)hs.getAttribute("abean");
			pw.println("Page belongs to "+ab.getfName()+"<br>");
			req.getRequestDispatcher("adminpage.html").include(req,res);
			int k=new UpdateProductDAO().update(req);
			if(k>0)
			{
				pw.println("<br>Product Updated Successfully...");
			}
		}
		else
		{
			pw.println("Session expired...<br>");
			req.getRequestDispatcher("homepage.html").include(req, res);
		}
	}

}
