package jdbcBasice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao {
public static void main(String[] args) {
	Connection connection=null;
	Statement statement=null;
	ResultSet rs=null;
	try {
		connection=DriverManager.getConnection("jdbc:mysql://localhost/my_db","root","Myroot@1");
		statement=connection.createStatement();
		rs=statement.executeQuery("select * from account");
		
		while(rs.next()) {
			int accno = rs.getInt(1);
			String lastname = rs.getString(2);
			String firstname = rs.getString(3);
			int bal = rs.getInt(4);
			System.out.println(accno+"||"+firstname+"||"+lastname+"||"+bal);
			
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

}
