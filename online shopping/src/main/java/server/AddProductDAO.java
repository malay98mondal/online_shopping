package server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class AddProductDAO 
{
	public int add(HttpServletRequest req)
	{
		int x=0;
		try
		{
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into product51 values(?,?,?,?)");
			ps.setString(1,req.getParameter("code"));
			ps.setString(2, req.getParameter("name"));
			ps.setFloat(3, Float.parseFloat(req.getParameter("price")));
			ps.setInt(4, Integer.parseInt(req.getParameter("qty")));
			x=ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return x;
	}
}
