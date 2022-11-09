

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private Connection connection;
     private PreparedStatement statement;
     ResultSet set;
     private String Search_Query="select * from register where username=?";
     private String Insert_Query="insert into register values(?,?)";

       
   
    public UserLogin() {
     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		out.print("<html><body align='center'><h3>inside servlet");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try {
			
			
		connection=Connection_web.getDatabase();
		out.print("<h3>database connected");
		statement=connection.prepareStatement(Search_Query);
		out.print("<h3>searching</body></html>");
		statement.setString(1, username);
		set=statement.executeQuery();
		out.print("query excuted");
		if(set.next())
			
		{
			out.print("<html> <script>");
			//out.print("alert('user already exists' )");	
			out.print("alert('username:' "+username+")");	
			out.print(" </script></html>");
			RequestDispatcher rd = request.getRequestDispatcher("signup.html");
			rd.include(request, response);
		}
		else 
		{
			
			connection=Connection_web.getDatabase();
			statement=connection.prepareStatement(Insert_Query);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.executeUpdate();
			out.print("<html> <script>");
			out.print("alert('Registration successfull')");	
			out.print(" </script></html>");
			RequestDispatcher rd = request.getRequestDispatcher("signup.html");
			rd.include(request, response);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
