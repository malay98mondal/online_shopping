package server;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private static Connection con;
	
	private DBConnection() {}
	
	static
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","malay","malay");
			System.out.println("Connect.................................");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		return con;
	}
}
