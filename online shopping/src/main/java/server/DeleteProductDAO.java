package server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteProductDAO 
{
	public int delete(String code)
	{
		int k=0;
		try
		{
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from product51 where code=?");
			ps.setString(1, code);
			k=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return k;
	}

}
