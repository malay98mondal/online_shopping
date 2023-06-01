package serveru;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.DBConnection;
import server.ProductBean;

public class ViewProductDAO 
{
	public ArrayList<ProductBean> retrieve()
	{
		ArrayList<ProductBean> alist=new ArrayList<ProductBean>();
		try
		{
			ResultSet rs=DBConnection.getConnection().prepareStatement("select * from product51").executeQuery();
			while(rs.next())
			{
				ProductBean pb=new ProductBean();
				pb.setCode(rs.getString(1));
				pb.setName(rs.getString(2));
				pb.setPrice(rs.getFloat(3));
				pb.setQty(rs.getInt(4));
				alist.add(pb);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return alist;
	}
}
