package serveru;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import server.DBConnection;

public class PurchaseDAO 
{
	public int purchase(HttpServletRequest req)
	{
		int x=0;
		try
		{
			Connection con=DBConnection.getConnection();
			PreparedStatement ps1=con.prepareStatement("select * from cart51 where uname=?");
			PreparedStatement ps2=con.prepareStatement("update product51 set qty=qty-? where code=?");
			PreparedStatement ps3=con.prepareStatement("delete from cart51 where uname=?");
			ps1.setString(1, req.getParameter("uname"));
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next())
			{
				ps2.setInt(1, rs1.getInt(2));
				ps2.setString(2, rs1.getString(1));
				x+=ps2.executeUpdate();
			}
			ps3.setString(1, req.getParameter("uname"));
			x+=ps3.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return x;
	}

}
