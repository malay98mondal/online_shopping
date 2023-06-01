package server;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AdminLoginDAO 
{
	public AdminBean login(HttpServletRequest req)
	{
		AdminBean ab=null;
		try
		{
			PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from admindetails51 where aid=? and pword=?");
			ps.setString(1, req.getParameter("aid"));
			ps.setString(2, req.getParameter("pword"));
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				ab=new AdminBean();
				ab.setAdminId(rs.getString(1));
				ab.setpWord(rs.getString(2));
				ab.setfName(rs.getString(3));
				ab.setlName(rs.getString(4));
				ab.setCity(rs.getString(5));
				ab.setmId(rs.getString(6));
				ab.setPhone(rs.getLong(7));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ab;
	}
}
