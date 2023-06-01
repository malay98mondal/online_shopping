package serveru;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import server.DBConnection;

public class RegisterUserDAO 
{
	public int register(HttpServletRequest req)
	{
		int x=0;
		try
		{
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into userdetails51 values(?,?,?,?,?,?,?)");
			ps.setString(1, req.getParameter("uname"));
			ps.setString(2, req.getParameter("pword"));
			ps.setString(3, req.getParameter("fname"));
			ps.setString(4, req.getParameter("lname"));
			ps.setString(5, req.getParameter("city"));
			ps.setString(6, req.getParameter("mid"));
			ps.setLong(7, Long.parseLong(req.getParameter("phno")));
			x=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return x;
	}
}
