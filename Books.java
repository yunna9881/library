package yunna;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class Books
{
	static Connection con;
	static PreparedStatement pre;
	
	private static String url = "jdbc:mysql://localhost:3306/publiclibrary";
	private static String user = "root";
	private static String pass = "wonyunnaA9881!";
	
	
	
	//add information for books
	public static void addBooks() throws Exception
	{
		Scanner in = new Scanner(System.in);
		
		con = DriverManager.getConnection(url, user, pass);
		
		pre = con.prepareStatement("INSERT INTO books (title, author, price, type, subject) VALUE (?,?,?,?,?)");
		
		try
		{
			
			System.out.println("Adding");
			System.out.println(" ");
			
			System.out.println("Enter title");
			String title = in.next();
			pre.setString(1, title);
			
			System.out.println("Enter author");
			String author = in.next();
			pre.setString(2, author);
		
			System.out.println("Enter price");
			BigDecimal price = in.nextBigDecimal();
			pre.setBigDecimal(3, price);
		
			System.out.println("Enter type");
			String type = in.next();
			pre.setString(4, type);
		
			System.out.println("Enter subject");
			String subject = in.next();
			pre.setString(5, subject);
			
			int row= pre.executeUpdate();
			System.out.println(row + " row added");
			

			}catch(Exception e)
			{
				e.printStackTrace();
			}finally
			{
				pre.close();
				con.close();
			}
	}
	
	
	// Update information for books
	public static void updateBooks() throws Exception
	{
		
		Scanner in = new Scanner(System.in);
		
		con = DriverManager.getConnection(url, user, pass);
		try
		{
			System.out.println("put a number");
			int number=in.nextInt();
			double price;
			String type;
			int code;
			String subject;
		
			switch(number)
			{
				case 1:

				String query = "UPDATE books SET price = ? where bookcode = ?";
				pre = con.prepareStatement(query);
				System.out.println("code, price");
				pre.setInt(2, code=in.nextInt());
				pre.setDouble(1, price = in.nextDouble());

				int result = pre.executeUpdate();
				System.out.println(result + " row updated");

				break;
				
				case 2:

				query = "UPDATE books SET type = ? where bookcode = ?";
				pre = con.prepareStatement(query);
				System.out.println("code, type");
				pre.setInt(2, code=in.nextInt());
				pre.setString(1, type = in.next());

				result = pre.executeUpdate();
				System.out.println(result + " row updated");

				break;
				
				case 3:
				
				query = "UPDATE books SET subject = ? where bookcode = ?";
				pre = con.prepareStatement(query);
				System.out.println("code, subject");
				pre.setInt(2, code=in.nextInt());
				pre.setString(1, type = in.next());

				result = pre.executeUpdate();
				System.out.println(result + " row updated");				
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
	
	

	
	
	//Delete information for books
	public static void deleteBooks() throws Exception
	{
		Scanner in = new Scanner(System.in);
		
		con = DriverManager.getConnection(url, user, pass);

		int bookcode;

		try
		{	
			String deleteQuery = "DELETE from books WHERE bookcode = ?";
			pre = con.prepareStatement(deleteQuery);				
			
			System.out.println("code number");
			pre.setInt(1, bookcode=in.nextInt());
			
			int result = pre.executeUpdate();
			System.out.println(result + " row deleted");
        }catch(Exception e){
            e.printStackTrace();
        }finally
		{
			pre.close();
			con.close();
		}
	}
	
	
	
	//show book table
	public static void showBooks() throws Exception
	{		
		Scanner in = new Scanner(System.in);
	
		con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
		try {
				ResultSet rs = st.executeQuery("select * from books");
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int colNumber = rsmd.getColumnCount();
				while (rs.next())
				{
					String colum1 = rs.getString("bookcode");
					String colum2 = rs.getString("title");
					String colum3 = rs.getString("author");
					int colum4 = rs.getInt("price");						
					String colum5 = rs.getString("type");
					String colum6 = rs.getString("subject");
					System.out.println(colum1 + ",\t" + colum2+ ",\t" + colum3
							+ ",\t" + colum4+ ",\t" + colum4+ ",\t" + colum5+ ",\t" + colum6);
				}
		}catch(Exception e){
            e.printStackTrace();
        }
	}

	
	public static void searchBooks() throws Exception
	{
		Scanner in = new Scanner(System.in);
		con = DriverManager.getConnection(url, user, pass);	
		try 
		{

			System.out.println("1.title 2.author");
			int number = in.nextInt();
			String titles;
			
			switch(number)
			{
				case 1:

				pre= con.prepareStatement("select * from books where title like ?");
				in.nextLine();
				System.out.println("Enter title");
				pre.setString(1, titles = "%"+in.nextLine() +"%");
				ResultSet result = pre.executeQuery();				
				while(result.next())
				{
					int bo = result.getInt("bookcode");
					String bo1 = result.getString("title");
					String bo2 = result.getString("author");
					double bo3 = result.getDouble("price");
					String bo4 = result.getString("type");
					String bo5 = result.getString("subject");
					System.out.print(bo + ",\t" + bo1 + ",\t" +bo2 + ",\t"+ bo3+",\t"
					+bo4 +",\t" + bo5);
				}
				break;
				
				case 2:
					String au;
					pre= con.prepareStatement("select * from books where author like ?");
					in.nextLine();
					System.out.println("Enter author");
					pre.setString(1,  au= "%"+in.nextLine() +"%");
					result = pre.executeQuery();				
					while(result.next())
					{
						int bo = result.getInt("bookcode");
						String bo1 = result.getString("title");
						String bo2 = result.getString("author");
						double bo3 = result.getDouble("price");
						String bo4 = result.getString("type");
						String bo5 = result.getString("subject");
						System.out.print(bo + ",\t" + bo1 + ",\t" +bo2 + ",\t"+ bo3+",\t"
						+bo4 +",\t" + bo5);
					}
					
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
	
	
	
}

	




