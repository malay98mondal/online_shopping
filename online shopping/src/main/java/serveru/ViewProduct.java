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

import server.ProductBean;

@WebServlet("/userview")
public class ViewProduct extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ViewProduct() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			UserBean ub=(UserBean)hs.getAttribute("ubean");
			pw.println("User: "+ub.getfName()+"<br><hr>");
			req.getRequestDispatcher("userpage.html").include(req, res);
			ArrayList<ProductBean> prod=new ViewProductDAO().retrieve();
			ArrayList<CartBean> cart=new CartDAO().retrieve(ub.getuName());
			if(prod.size()==0)
			{
				pw.println("<br>Product not available...");
			}
			else
			{
				hs.setAttribute("plist", prod);
				hs.setAttribute("clist", cart);
				Iterator<ProductBean> it=prod.iterator();
				out:
				while(it.hasNext())
				{
					ProductBean pb=(ProductBean)it.next();
					Iterator<CartBean> cb=cart.iterator();
					while(cb.hasNext())
					{
						if(((CartBean)cb.next()).getCode().equals(pb.getCode()))
						{
							pw.println(pb.getName()+"&nbsp&nbsp&nbsp"+pb.getPrice()+"&nbsp&nbsp&nbsp<div><input type='number' value='1' max='"+pb.getQty()+
									"' min='1' required>&nbsp&nbsp&nbsp<input type='hidden' value='"+pb.getCode()+"' name='code'><button>Added</button></div>");
							continue out;
						}
						
					}
					if(pb.getQty()<=0)
					{
						pw.println(pb.getName()+"&nbsp&nbsp&nbsp"+pb.getPrice()+"&nbsp&nbsp&nbsp<div><input type='number' value='1' max='"+pb.getQty()+
								"' min='1' required>&nbsp&nbsp&nbsp<input type='hidden' value='"+pb.getCode()+"' name='code'><button>Unavailable</button></div>");
						
					}
					else
					{
						pw.println(pb.getName()+"&nbsp&nbsp&nbsp"+pb.getPrice()+"&nbsp&nbsp&nbsp<form action='addtocart' method='post'><input type='number' value='1' max='"+pb.getQty()+
							"' min='1' name='qty' required>&nbsp&nbsp&nbsp<input type='hidden' value='"+pb.getCode()+"' name='code'><input type='hidden' name='uname' value='"+
								ub.getuName()+"'><button>Add to Cart</button></form>");
					}
					
				
				}
				
			}
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession hs=req.getSession(false);
		if(hs!=null)
		{
			UserBean ub=(UserBean)hs.getAttribute("ubean");
			pw.println("User: "+ub.getfName()+"<br><hr>");
			req.getRequestDispatcher("userpage.html").include(req, res);
			ArrayList<ProductBean> prod=new ViewProductDAO().retrieve();
			ArrayList<CartBean> cart=new CartDAO().retrieve(ub.getuName());
			if(prod.size()==0)
			{
				pw.println("<br>Product not available...");
			}
			else
			{
				hs.setAttribute("plist", prod);
				hs.setAttribute("clist", cart);
				Iterator<ProductBean> it=prod.iterator();
				out:
				while(it.hasNext())
				{
					ProductBean pb=(ProductBean)it.next();
					Iterator<CartBean> cb=cart.iterator();
					while(cb.hasNext())
					{
						if(((CartBean)cb.next()).getCode().equals(pb.getCode()))
						{
							pw.println(pb.getName()+"&nbsp&nbsp&nbsp"+pb.getPrice()+"&nbsp&nbsp&nbsp<div><input type='number' value='1' max='"+pb.getQty()+
									"' min='1' required>&nbsp&nbsp&nbsp<input type='hidden' value='"+pb.getCode()+"' name='code'><button>Added</button></div>");
							continue out;
						}
						
					}
					if(pb.getQty()<=0)
					{
						pw.println(pb.getName()+"&nbsp&nbsp&nbsp"+pb.getPrice()+"&nbsp&nbsp&nbsp<div><input type='number' value='1' max='"+pb.getQty()+
								"' min='1' required>&nbsp&nbsp&nbsp<input type='hidden' value='"+pb.getCode()+"' name='code'><button>Unavailable</button></div>");
						
					}
					else
					{
						pw.println(pb.getName()+"&nbsp&nbsp&nbsp"+pb.getPrice()+"&nbsp&nbsp&nbsp<form action='addtocart' method='post'><input type='number' value='1' max='"+pb.getQty()+
							"' min='1' name='qty' required>&nbsp&nbsp&nbsp<input type='hidden' value='"+pb.getCode()+"' name='code'><input type='hidden' name='uname' value='"+
								ub.getuName()+"'><button>Add to Cart</button></form>");
					}
					
				
				}
				
			}
		}
	}

	

}
