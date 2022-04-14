package movies;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class assessment 
{
	Connection conn = null;
	static String url = "jdbc:sqlite:/E:\\SQLite\\sqlite-tools-win32-x86-3380200\\sqlite-tools-win32-x86-3380200\\userdata.db";

	
	public static void main(String args[]) throws SQLException
	{
		
		connect();
		createTable();
		assessment obj = new assessment();
		obj.insertMovie("Quantum of Solace","Daniel Craig", "Olga", "Marc Forster", 2008);
		obj.insertMovie("Spectre","Daniel Craig", "Lea", "Sam Mendes", 2015);
		obj.insertMovie("Thor","Chris Hemsworth", "Natalie", "Kenneth Branagh", 2011);
		obj.insertMovie("Fast and Furious 8","Paul Walker", "Vin Diesel", "Gary Gray", 2017);
		obj.insertMovie("Padmaavat","Ranveer Singh", "Deepika", "Bhansali", 2018);
		obj.insertMovie("Casino Royale","Daniel Craig", "Eva", "MArtin", 2006);
		
		assessment.displayQuery();
	}
	
		@SuppressWarnings("unused")
		public static void connect() throws SQLException
		{
			
		try
		{
			String url = "jdbc:sqlite:/E:\\SQLite\\sqlite-tools-win32-x86-3380200\\sqlite-tools-win32-x86-3380200\\userdata.db";
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(url);
			System.out.println("DB opened successfully");
			
			
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
			System.out.println(e.getClass().getName()+": "+e.getMessage());
			
		} 
		
		finally
		{
			String url = "jdbc:sqlite:/E:\\SQLite\\sqlite-tools-win32-x86-3380200\\sqlite-tools-win32-x86-3380200\\userdata.db";
			//Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(url);
			try
			{
			if(conn!=null)
				{
					conn.close();
				}
			}
				
			catch(SQLException e)
				{
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		}
	
	
	public static void createTable() throws SQLException
	{
		//String url = "jdbc:sqlite:/E:\\SQLite\\sqlite-tools-win32-x86-3380200\\sqlite-tools-win32-x86-3380200\\userdata.db";
		
		String crTable = "" +
				"CREATE TABLE Movies "+
				"("+
				"title varchar(70), "+
				"actor varchar(70), "+
				"actress varchar(70), "+
				"dir varchar(70), "+
				"year integer, "+
				") "+
				"";
		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(crTable);
		}
		catch(SQLException e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		
	}
	
	public void insertMovie( String title, String actor, String actress, String dir,int year) throws SQLException
	{

		String sql = " INSERT INTO Movies(title,actor,actress,dir,year)VALUES(?,?,?,?,?)";
		
		
		try {
			

			String url = "jdbc:sqlite:/E:\\SQLite\\sqlite-tools-win32-x86-3380200\\sqlite-tools-win32-x86-3380200\\userdata.db";
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,title);
			pst.setString(2,actor);
			pst.setString(3, actress);
			pst.setString(4,dir);
			pst.setInt(5,year);
			pst.executeUpdate();
		}
			
		
		catch(SQLException | ClassNotFoundException e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		
		//System.out.println("Records created successfully");
	}
	
	public static void displayQuery()throws SQLException
	{
		//String sql = " INSERT INTO Movies(title,actor,actress,dir,year)VALUES(?,?,?,?,?)";
		String selectSQL = "SELECT * from  Movies WHERE actor = 'Daniel Craig' ";

		try {
		String url = "jdbc:sqlite:/E:\\SQLite\\sqlite-tools-win32-x86-3380200\\sqlite-tools-win32-x86-3380200\\userdata.db";
        Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(url);	
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(selectSQL);
		
		
		while(rs.next()) {
		System.out.println(rs.getString("title"));
		System.out.println(rs.getString("actor"));
		System.out.println(rs.getString("actress"));
		System.out.println(rs.getString("dir"));
		System.out.println(rs.getInt("year"));
		}
		}
		catch (Exception e)
		{
		
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
		}		
	}
	
}



