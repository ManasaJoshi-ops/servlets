package com.webapp.add;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AddUserServlet() {
        super();
            }
    Connection connection=null;
public void init() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/my_db","root","Myroot@1");
	} catch (SQLException | ClassNotFoundException e) {
		
		e.printStackTrace();
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");		
		String lastname = request.getParameter("lastname");	
		String email = request.getParameter("email");	
		String password = request.getParameter("password");	
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try (
				Statement statement=connection.createStatement();){
				int Result =statement.executeUpdate("insert into user values('"+firstname+"','"+lastname+"','"+email+"','"+password+"')");
                if (Result>0) {
                	out.println("<h1>User Created in db</h1>");
                }else {
                	out.println("<h1>Error creating user</h1>");
                }
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

}
