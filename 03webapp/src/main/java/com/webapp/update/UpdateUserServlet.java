package com.webapp.update;

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

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection=null;
	
  
    public UpdateUserServlet() {
        super();
            }
    public void init() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/my_db","root","Myroot@1");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		try (Statement statement =connection.createStatement();){
			int Result=statement.executeUpdate("update user set password='"+password+"' where email='"+email+"'");
			
			if(Result>0) {
				out.println("Password updated");
			}else {
				out.println("Error changing password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void destroy() {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
