package serveru;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterUser extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		int x=new RegisterUserDAO().register(req);
		if(x>0)
		{
			pw.println("User registration Successfull...<br>");
		}
		else
		{
			pw.println("User registration failed...<br>");
		}
		req.getRequestDispatcher("userlogin.html");
	}

}
