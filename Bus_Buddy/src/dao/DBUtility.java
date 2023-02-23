package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import common.CommanAmongAll;

public class DBUtility {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(CommanAmongAll.RED+"Something is wrong, couldn't connect to Database\n"+CommanAmongAll.RESET);
		}
	}
	
	public static final Connection getConnection() throws SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("dbdetails");
		String url = rb.getString("url");
		String username = rb.getString("username");
		String password = rb.getString("password");
		
		return DriverManager.getConnection(url, username, password);
	}
	
	public static final void closeConnection(Connection con) throws SQLException {
		con.close();
	}
}
