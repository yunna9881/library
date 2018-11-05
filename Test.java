package yunna;

import java.sql.*;
import java.util.Scanner;

public class Test {
	static Connection con;
	static PreparedStatement pre;
	public static void main(String[] args) throws Exception
	{
	
		Connect.ConnectDB();
		Test.option();

		
	}

	public static void option() throws Exception
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Select a option" + "\n1. Books \n2. Branch \n3. Library \n4. Quit : ");
		int option = in.nextInt();
	
		try 
		{
			if(option == 1)
			{
				System.out.println("1.add 2.update 3.delete 4.show 5.search 6.quit");
				
				int num = in.nextInt();
				switch(num)
				{
					case 1:
						Books.addBooks();
						break;
						
					case 2:
						Books.updateBooks();
						break;
						
					case 3:
						Books.deleteBooks();
						break;
						
					case 4:
						Books.showBooks();
						break;
						
					case 5:
						Books.searchBooks();
						break;
						
					case 6:
						System.exit(0);
					
				}			
			}else if(option == 2)
			{
				System.out.println("1.add 2.update 3.delete 4.show 5.show 6.not'beaches' 7.quit");
				
				int num = in.nextInt();
				switch(num)
				{
					case 1:
						Branch.addBranch();
						break;
						
					case 2:
						Branch.updateBranch();
						break;
						
					case 3:
						Branch.deleteBranch();
						break;
						
					case 4:
						Branch.showBranch();
						break;
						
					case 5:
						Branch.searchBranch();
						break;
						
					case 6:
						Branch.bookBranch();
						
					case 7:
						System.exit(0);
						
				}
			}else if(option == 3)
			{
				System.out.println("1.add 2.update 3.delete 4.show 5.search 6.quit 7.list of Technology 8.fine amount 9.late 10.quit");
				
				int num = in.nextInt();
				switch(num)
				{
					case 1:
						Library.addLibrary();
						break;
						
					case 2:
						Library.updateLibrary();
						break;
						
					case 3:
						Library.deleteLibrary();
						break;
						
					case 4:
						Library.showLibrary();
						break;
						
					case 5:
						Library.searchLibrary();
						break;
						
					case 7:
						Library.listTech();
						break;
					
					case 8:
						Library.fine();
						break;
						
					case 9:
						Library.late();
						
					case 10:
						System.exit(0);
				}
			}else if(option == 4)
			{
				System.exit(0);	
			}else 
			{
				System.out.println("Select correct option");	
			}
		}catch(Exception e)
			{
				e.printStackTrace();
			}
				finally
			{
				pre.close();
				con.close();	
			}
	}
}


