package yunna;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Library {
	static Connection con;
	static PreparedStatement pre;

	private static String url = "jdbc:mysql://localhost:3306/publiclibrary";
	private static String user = "root";
	private static String pass = "";

	// add information for library
	public static void addLibrary() throws Exception {
		Scanner in = new Scanner(System.in);

		con = DriverManager.getConnection(url, user, pass);

		pre = con
				.prepareStatement("INSERT INTO library (branchcode, bookcode, borrowdate, returndate) VALUE (?,?,?,?)");

		String bor;
		String re;

		try {

			System.out.println("Adding");
			System.out.println(" ");

			System.out.println("Enter branchcode");
			int branchCode = in.nextInt();
			pre.setInt(1, branchCode);

			System.out.println("Enter bookcode");
			int bookcode = in.nextInt();
			pre.setInt(2, bookcode);

			System.out.println("Enter borrowdate");
			Date date1;
			System.out.println("yyyymmdd");
			bor = in.next();
			date1 = new java.sql.Date((new SimpleDateFormat("yyyyMMdd").parse(bor)).getTime());
			pre.setDate(3, date1);

			System.out.println("Enter returndate");
			Date date2;
			System.out.println("yyyymmdd");
			re = in.next();
			date2 = new java.sql.Date((new SimpleDateFormat("yyyyMMdd").parse(re)).getTime());
			pre.setDate(4, date2);

			int row = pre.executeUpdate();
			System.out.println(row + " row added");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pre.close();
			con.close();
		}
	}

	// Update information for library
	public static void updateLibrary() throws Exception {
		Scanner in = new Scanner(System.in);
		con = DriverManager.getConnection(url, user, pass);

		try {
			int branchcode;
			int bookcode;
			String bor;
			String re;

			System.out.println("1. borrowdate 2. returndate");
			int num = in.nextInt();
			switch (num) {
			case 1:
				String query = "UPDATE library SET borrowdate = ? where branchcode = ?";
				pre = con.prepareStatement(query);
				System.out.println("Enter branchcode");
				pre.setInt(2, branchcode = in.nextInt());
				Date date1;
				System.out.println("yyyymmdd");
				bor = in.next();
				date1 = new java.sql.Date((new SimpleDateFormat("yyyyMMdd").parse(bor)).getTime());
				pre.setDate(1, date1);

				int row = pre.executeUpdate();
				System.out.println(row + " row updated");
				break;

			case 2:
				query = "UPDATE library SET returndate = ? where branchcode = ?";
				pre = con.prepareStatement(query);
				System.out.println("Enter branchcode");
				pre.setInt(2, branchcode = in.nextInt());
				Date date2;
				System.out.println("yyyymmdd");
				re = in.next();
				date2 = new java.sql.Date((new SimpleDateFormat("yyyyMMdd").parse(re)).getTime());
				pre.setDate(1, date2);

				row = pre.executeUpdate();
				System.out.println(row + " row updated");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pre.close();
			con.close();
		}

	}

	// Delete information for library
	public static void deleteLibrary() throws Exception {
		Scanner in = new Scanner(System.in);
		con = DriverManager.getConnection(url, user, pass);
		int branchcode;

		try {
			String deleteQuery = "DELETE from library WHERE branchcode = ?";
			pre = con.prepareStatement(deleteQuery);

			System.out.println("Enter branchcode");
			pre.setInt(1, branchcode = in.nextInt());

			int result = pre.executeUpdate();
			System.out.println(result + " row deleted");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pre.close();
			con.close();
		}
	}
	
	//show library table
	public static void showLibrary() throws Exception
	{		
		Scanner in = new Scanner(System.in);
	
		con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
		try {
				ResultSet rs = st.executeQuery("select * from library");
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int colNumber = rsmd.getColumnCount();
				while (rs.next())
				{
					int colum1 = rs.getInt("branchcode");
					int colum2 = rs.getInt("bookcode");
					String colum3 = rs.getString("borrowdate");
					String colum4 = rs.getString("returndate");						
					System.out.println(colum1 + ",\t" + colum2+ ",\t" + colum3
							+ ",\t" + colum4+ ",\t" + colum4);
				}
		}catch(Exception e){
            e.printStackTrace();
        }
	}

	public static void searchLibrary() throws Exception
	{
		Scanner in = new Scanner(System.in);
		con = DriverManager.getConnection(url, user, pass);	
		try 
		{
			int bran;
			pre= con.prepareStatement("select * from library where branchcode = ?");
			System.out.println("Enter branchCode");
			pre.setInt(1, bran =in.nextInt());
			ResultSet result = pre.executeQuery();				
			while(result.next())
			{
				int bo = result.getInt("branchcode");
				String bo1 = result.getString("bookcode");
				String bo2 = result.getString("borrowdate");
				String bo3 = result.getString("returndate");
				String bo4 = result.getString("findamount");
				System.out.print(bo + ",\t" + bo1 + ",\t" +bo2 + ",\t"+ bo3+",\t" + bo4);
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			pre.close();
			con.close();
		}
	}
	
	
	public static void listTech() throws Exception
	{
		Scanner in = new Scanner(System.in);
		con = DriverManager.getConnection(url, user, pass);	
		Statement st = con.createStatement();
		try 
		{
			
			ResultSet rs = st.executeQuery("select branchcode, count(bookcode) as number "
					+ "from library where bookcode in"
					+ "(select bookcode from books where type = 'Technology') "
					+ "group by branchcode");

			while(rs.next())
			{
				int branchcode = rs.getInt("branchcode");
				int number = rs.getInt("number");
				
				System.out.println("BranchCode : " + branchcode + "\tAmount : " + number);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static void fine() throws Exception
	{
		Scanner in = new Scanner(System.in);
		con = DriverManager.getConnection(url, user, pass);	
		Statement st = con.createStatement();
		try 
		{
			
			ResultSet rs = st.executeQuery("select branchcode, sum(fineamount) as fineamount "
					+ "from library "
					+ "group by branchcode");

			while(rs.next())
			{
				int branchcode = rs.getInt("branchcode");
				double fineamount = rs.getDouble("fineamount");
				
				System.out.println("BranchCode : " + branchcode + "\tFine Amount : " + fineamount);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void late() throws Exception
	{
		Scanner in = new Scanner(System.in);
		
		con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
		try {

			int branch;
			int book;
				pre= con.prepareStatement( "SELECT bookcode, borrowdate, returndate,"
						+ "TIMESTAMPDIFF(DAY,borrowdate,returndate) AS outDays " + "FROM library"
						+ " where branchcode = ? and bookcode = ?");

				
				pre.setInt(1, branch = in.nextInt());
				pre.setInt(2, book=in.nextInt());
				ResultSet result = pre.executeQuery();	
				while(result.next())
					
				{
					int day = result.getInt(4);
					System.out.println(day);
					
				}
				
		}catch(Exception e){
            e.printStackTrace();
        }
	}
	

}
