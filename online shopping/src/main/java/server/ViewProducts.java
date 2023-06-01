package server;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet("/view")
public class ViewProducts extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			AdminBean ab=(AdminBean)hs.getAttribute("abean");
			pw.println("Page belongs to: "+ab.getfName()+"<br>");
			req.getRequestDispatcher("adminpage.html").include(req,res);
			
			LinkedList<ProductBean> prod=new ViewProductsDAO().retrieve();
			if(prod.size()==0)
			{
				pw.println("<br>Products not available...<br>");
			}
			else
			{
				hs.setAttribute("plist", prod);
				Iterator<ProductBean> it=prod.iterator();
				while(it.hasNext())
				{
					ProductBean pb=it.next();
					pw.println("<br>"+pb.getCode()+"&nbsp&nbsp"+pb.getName()+"&nbsp&nbsp"+pb.getPrice()+"&nbsp&nbsp"+pb.getQty()+
							"&nbsp&nbsp<a href='edit?pcode="+pb.getCode()+"'>Edit</a>&nbsp&nbsp&nbsp<a href='delete?pcode="+pb.getCode()+"'>Delete</a>");
				}//end of loop
			}//end of else
			
		}
		else
		{
			pw.println("<br>Session Expired<br>");
			req.getRequestDispatcher("homepage.html").include(req, res);
		}
	}

}
