package serveru;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import server.DBConnection;

public class RemoveProductFromCartDAO 
{
	public int remove(HttpServletRequest req)
	{
		int x=0;
		try
		{
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from cart51 where pcode=? and uname=?");
			ps.setString(1, req.getParameter("pcode"));
			ps.setString(2, req.getParameter("uname"));
			x=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return x;
	}
}
