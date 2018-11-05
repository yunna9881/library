package yunna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Connect {
	static Connection con;
	static PreparedStatement pre;
	
	private static String url = "jdbc:mysql://localhost:3306/publiclibrary";
	private static String user = "root";
	private static String pass = "wonyunnaA9881!";
	

	public static Connection ConnectDB() throws Exception
	{		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);

			return con;	
		}catch (SQLException e)
		{
			System.out.println("Error : Connection Failed" + e.getMessage());
			return null;
		} 
	}
}
