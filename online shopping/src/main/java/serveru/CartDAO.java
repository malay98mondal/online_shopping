package serveru;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.DBConnection;

public class CartDAO 
{
	public ArrayList<CartBean> retrieve(String uname)
	{
		ArrayList<CartBean> cart=new ArrayList<CartBean>();
		try
		{
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("select name, price, pcode, qnity from product51 p inner join cart51 c on p.code=c.pcode where c.uname=?");
			ps.setString(1, uname);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CartBean cb=new CartBean();
				cb.setName(rs.getString(1));
				cb.setPrice(Float.parseFloat(rs.getString(2)));
				cb.setCode(rs.getString(3));
				cb.setQty(Integer.parseInt(rs.getString(4)));
				cb.setUname(uname);
				
				cart.add(cb);				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return cart;
	}
}
