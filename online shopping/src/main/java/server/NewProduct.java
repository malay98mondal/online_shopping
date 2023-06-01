package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/newproduct")
public class NewProduct extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doGet( HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			AdminBean ab=(AdminBean)hs.getAttribute("abean");
			pw.println("page belongs to "+ab.getfName()+"<br>");
			pw.println("<form action='add' method='post'>");
			pw.println("Product code <input type='text' name='code'><br>");
			pw.println("Product name <input type='text' name='name'><br>");
			pw.println("Product Price <input type='text' name='price'><br>");
			pw.println("Product Qty <input type='text' name='qty'><br>");
			pw.println("<input type='submit' value='Add Product'>");
			pw.println("</form>");
			
		}
		else
		{
			pw.println("Session expired...");
			req.getRequestDispatcher("homepage.html").include(req, res);
		}
		
	}
	
}
