
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_web {
	private static final String DRIVER="com.mysql.jdbc.Driver"; 
	private static final String URL="jdbc:mysql://localhost:3306/";
	private static final String DATABASE="login";
	private static final String USERNAME="root";
	private static final String PASSWORD="root"; 
	private static Connection connection;
	public static Connection getDatabase()
	{
	
	try 
	{
		Class.forName(DRIVER);
		connection=DriverManager.getConnection(URL+DATABASE,USERNAME,PASSWORD);
		System.out.println("CONNECTION ESTABLISHED");
	} 
	
	
	catch (ClassNotFoundException e) 
	
	{
		
		e.printStackTrace();
	}
	
		
	 catch (SQLException e) {
	
		e.printStackTrace();
	}
	
	
	return connection;
	
	}
	
	
public static void main(String[] args) {
	Connection_web.getDatabase();
}
}
