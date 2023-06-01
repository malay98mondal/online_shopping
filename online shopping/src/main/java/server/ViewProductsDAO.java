package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ViewProductsDAO 
{
	public LinkedList<ProductBean> retrieve()
	{
		LinkedList<ProductBean> prod=new LinkedList<ProductBean>();
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
				prod.add(pb);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return prod;
	}

}
