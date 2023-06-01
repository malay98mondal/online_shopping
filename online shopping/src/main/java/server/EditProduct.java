package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/edit")
public class EditProduct extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			AdminBean ab=(AdminBean)hs.getAttribute("abean");
			@SuppressWarnings("unchecked")
			LinkedList<ProductBean> prod=(LinkedList<ProductBean>)hs.getAttribute("plist");
			pw.println("page belongs to "+ab.getfName()+"<br>");
			String code=req.getParameter("pcode");
			
			List<ProductBean> l= prod.stream().filter((x)->x.getCode().equals(code)).collect(Collectors.toList());
			l.forEach((k)->
			{
				ProductBean pb=(ProductBean)k;
				pw.println("<form action='update' method='post'>");
				pw.println("<input type='hidden' name='code' value='"+pb.getCode()+"'>");
				pw.println("<input type='text' name='price' value='"+pb.getPrice()+"'><br>");
				pw.println("<input type='text' name='qty' value='"+pb.getQty()+"'><br>");
				pw.println("<input type='submit' value='Update Product'>");
				pw.println("</form>");
			});
			
		}
		else
		{
			pw.println("Session Expired...<br>");
			req.getRequestDispatcher("homepage.html").include(req, res);
		}
	}
}
