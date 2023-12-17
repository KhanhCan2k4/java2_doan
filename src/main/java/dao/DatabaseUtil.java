package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		if (connection == null ) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/doanjava_db?characterEncoding=utf8",
						"root",
						"");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("ClassNotFoundException " + e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("SQLException " + e.getMessage());
			}
		}
		
		return connection;
	}

}
