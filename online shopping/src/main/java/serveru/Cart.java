package serveru;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/cart")
public class Cart extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			UserBean ub=(UserBean)hs.getAttribute("ubean");
			pw.println("User: "+ub.getfName()+"<br><hr>");
			req.getRequestDispatcher("userpage.html").include(req, res);
			ArrayList<CartBean> cart=new CartDAO().retrieve(ub.getuName());
			hs.setAttribute("clist", cart);
			pw.println("<b>Name&nbsp&nbsp&nbspQty&nbsp&nbsp&nbspPrice</b></br>");
			if(cart.size()>0)
			{
				float tprice=0;
				Iterator<CartBean> icart=cart.iterator();
				while(icart.hasNext())
				{
					CartBean c=icart.next();
					pw.println(c.getName()+"&nbsp&nbsp&nbsp"+c.getQty()+"&nbsp&nbsp&nbsp"+c.getQty()*c.getPrice()+"&nbsp&nbsp&nbsp<a href='remove?pcode="+c.getCode()+
							"&uname="+c.getUname()+"'><button>Remove</button></a><br>");
					tprice+=c.getPrice()*c.getQty();
					
				}
				pw.println("<hr><br><b><form action='purchase' method='post'>Total Price <input type='text' value='"+tprice+
						"' name='tprice' readonly><input type='hidden' name='uname' value='"+ub.getuName()+"'><button>Purchase</button>");
			}
		}
	}

}
