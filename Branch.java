package yunna;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class Branch 
{
	static Connection con;
	static PreparedStatement pre;
	
	private static String url = "jdbc:mysql://localhost:3306/publiclibrary";
	private static String user = "root";
	private static String pass = "wonyunnaA9881!";
	
	//add information for branch
	public static void addBranch() throws Exception
	{
		Scanner in = new Scanner(System.in);
		
		con = DriverManager.getConnection(url, user, pass);
		pre = con.prepareStatement("INSERT INTO branch (branchname, address, postalcode) VALUE (?,?,?)");
		
		try
		{
			System.out.println("Adding");
			System.out.println(" ");

			System.out.println("Enter branchname");
			String branchname = in.next();
			pre.setString(1, branchname);
		
			System.out.println("Enter address");
			String address = in.next();
			pre.setString(2, address);
		
			System.out.println("Enter postalcode");
			String postalcode= in.next();
			pre.setString(3, postalcode);
		
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
	
	
	//update information for branch
	public static void updateBranch() throws Exception
	{
		Scanner in = new Scanner(System.in);
		
		con = DriverManager.getConnection(url, user, pass);
		try
		{
			System.out.println("put a number");
			int number=in.nextInt();
			int branchcode;
			String branchname;
			String address;
			String postalcode;
		
			switch(number)
			{
				case 1:

				String query = "UPDATE branch SET branchname = ? where branchcode = ?";
				pre = con.prepareStatement(query);
				System.out.println("branchcode, branchname");
				pre.setInt(2, branchcode=in.nextInt());
				pre.setString(1, branchname = in.next());

				int result = pre.executeUpdate();
				System.out.println(result + " row updated");

				break;
				
				case 2:

				query = "UPDATE branch SET address = ? where branchcode = ?";
				pre = con.prepareStatement(query);
				System.out.println("code, address");
				pre.setInt(2, branchcode=in.nextInt());
				pre.setString(1, address = in.next());

				result = pre.executeUpdate();
				System.out.println(result + " row updated");

				break;
				
				case 3:
				
				query = "UPDATE books SET postalcode = ? where branchcode = ?";
				pre = con.prepareStatement(query);
				System.out.println("branchcode, postalcode");
				pre.setInt(2, branchcode=in.nextInt());
				pre.setString(1, postalcode = in.next());

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

	//Delete information for branch
	public static void deleteBranch() throws Exception
	{
		Scanner in = new Scanner(System.in);
		
		con = DriverManager.getConnection(url, user, pass);

		int branchcode;
		try
		{	
			String deleteQuery = "DELETE from branch WHERE branchcode = ?";
			pre = con.prepareStatement(deleteQuery);				
			
			System.out.println("branchcode number");
			pre.setInt(1, branchcode=in.nextInt());
			
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
	
	//show branch table
	public static void showBranch() throws Exception
	{		
		Scanner in = new Scanner(System.in);
	
		con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
		try
		{
			ResultSet rs = st.executeQuery("select * from branch");
//			ResultSetMetaData rsmd = rs.getMetaData();
				
//			int colNumber = rsmd.getColumnCount();
			while (rs.next())
			{
				int colum1 = rs.getInt("branchcode");
				String colum2 = rs.getString("branchname");
				String colum3 = rs.getString("address");
				String colum4 = rs.getString("postalcode");

				System.out.println(colum1 + ",\t" + colum2+ ",\t" + colum3
									+ ",\t" + colum4);
				}
		}catch(Exception e){
            e.printStackTrace();
        }
	}
	
	
	public static void searchBranch() throws Exception
	{
		Scanner in = new Scanner(System.in);
		con = DriverManager.getConnection(url, user, pass);	
		try 
		{

			System.out.println("1.branch name 2.postal code");
			int number = in.nextInt();
			String bran;
			
			switch(number)
			{
				case 1:

				pre= con.prepareStatement("select * from branch where branchname like ?");
				in.nextLine();
				System.out.println("Enter branch name");
				pre.setString(1, bran = "%"+in.nextLine() +"%");
				ResultSet result = pre.executeQuery();				
				while(result.next())
				{
					int bo = result.getInt("branchcode");
					String bo1 = result.getString("branchname");
					String bo2 = result.getString("address");
					String bo3 = result.getString("postalcode");
					System.out.print(bo + ",\t" + bo1 + ",\t" +bo2 + ",\t"+ bo3+",\t");
				}
				break;
				
				case 2:
					String post;
					pre= con.prepareStatement("select * from branch where postalcode like ?");
					in.nextLine();
					System.out.println("Enter postalcode without space");
					pre.setString(1,  post= "%"+in.nextLine() +"%");
					result = pre.executeQuery();				
					while(result.next())
					{
						int bo = result.getInt("branchcode");
						String bo1 = result.getString("branchname");
						String bo2 = result.getString("address");
						String bo3 = result.getString("postalcode");
						System.out.print(bo + ",\t" + bo1 + ",\t" +bo2 + ",\t"+ bo3+",\t");
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
	
	
	public static void bookBranch() throws Exception
	{
		Scanner in = new Scanner(System.in);
		con = DriverManager.getConnection(url, user, pass);	
		try 
		{
			pre= con.prepareStatement("select * from branch where branchname != 'Beaches'");
			ResultSet result = pre.executeQuery();				
			while(result.next())
			{
				int bo = result.getInt("branchcode");
				String bo1 = result.getString("branchname");
				String bo2 = result.getString("address");
				String bo3 = result.getString("postalcode");
				System.out.println(bo + ",\t" + bo1 + ",\t" +bo2 + ",\t"+ bo3+",\t");
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
	
