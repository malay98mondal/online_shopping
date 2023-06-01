package serveru;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import server.DBConnection;

public class UserLoginDAO 
{
	public UserBean login(HttpServletRequest req)
	{
		UserBean ub=null;
		try
		{
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from userdetails51 where uname=? and pword=?");
			ps.setString(1, req.getParameter("uname"));
			ps.setString(2, req.getParameter("pword"));
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				ub=new UserBean();
				ub.setuName(rs.getString(1));
				ub.setpWord(rs.getString(2));
				ub.setfName(rs.getString(3));
				ub.setlName(rs.getString(4));
				ub.setCity(rs.getString(5));
				ub.setMid(rs.getString(6));
				ub.setPhone(rs.getLong(7));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return ub;
	}
}
