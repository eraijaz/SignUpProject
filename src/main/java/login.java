
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private Connection connection;
       private PreparedStatement statement;
       ResultSet set;
       private String Search_Query="select * from mylogin where username=?";
       private String Insert_Query="insert into mylogin values(?,?)";
  
    public login() {
        super();

    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
			PrintWriter out =response.getWriter();
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			
			
			
			try
			{ 	connection=Connection_web.getDatabase();
				statement=connection.prepareStatement(Search_Query);
				statement.setString(1, username);
				//statement.setString(2, username);
				set=statement.executeQuery();
				if(set.next())
					
				{
					out.print("<html> <script>");
					out.print("alert('user already exists')");	
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
					out.print("<html><h3>Registration Successsfull<h3></html>");
				//	response.sendRedirect("Stdregister.html");
					//response.setIntHeader("Refresh", 2);
					//RequestDispatcher rd = request.getRequestDispatcher("Adminhome.jsp");
					//rd.include(request, response);
				}
			
			}
			
			catch(SQLException s)
			{
				s.printStackTrace();
			}
			
}
}