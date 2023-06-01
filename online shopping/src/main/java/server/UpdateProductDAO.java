package server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class UpdateProductDAO 
{
	public int update(HttpServletRequest req)
	{
		int k=0;
		try
		{
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("update product51 set price=?, qty=? where code=?");
			ps.setFloat(1, Float.parseFloat(req.getParameter("price")));
			ps.setInt(2, Integer.parseInt(req.getParameter("qty")));
			ps.setString(3, req.getParameter("code"));
			k=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return k;
	}

}
