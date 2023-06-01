package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/delete")
public class DeleteProduct extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			AdminBean ab=(AdminBean)hs.getAttribute("abean");
			String code=req.getParameter("pcode");
			pw.println("page belongs to "+ab.getfName()+"<br>");
			req.getRequestDispatcher("adminpage.html").include(req,res);
			int k=new DeleteProductDAO().delete(code);
			if(k>0)
			{
				pw.println("<br>Product deleted Successfully...");
			}
		}
	}

}
