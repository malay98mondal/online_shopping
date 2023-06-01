package serveru;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import server.DBConnection;

public class AddToCartDAO 
{
	public int add(HttpServletRequest req)
	{
		int x=0;
		try
		{
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into cart51 values(?,?,?)");
			ps.setString(1, req.getParameter("code"));
			ps.setInt(2, Integer.parseInt(req.getParameter("qty")));
			ps.setString(3, req.getParameter("uname"));
			x=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return x;
	}
}
