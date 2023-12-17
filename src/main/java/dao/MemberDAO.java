package dao;

import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class MemberDAO {
	public static void main(String[] args) {
		System.out.println(isAdmin("admin@gmail.com", "121"));
	}
	
	public static boolean isMember(String email, String password) {
		String sql = "SELECT * FROM `members` WHERE email = ? AND password = ?";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setString(1, email);
			p.setString(2, password);
			
			return p.executeQuery().next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean isAdmin(String email, String password) {
		String sql = "SELECT * FROM `members` WHERE email = ? AND password = ? AND role = 1";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setString(1, email);
			p.setString(2, password);
			
			return p.executeQuery().next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
