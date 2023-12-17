package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import core.Category;

public class CategoryDAO {
	
	public static void main(String[] args) {
		System.out.println(getCategoryById(3));
	}
	
	public static ArrayList<Category> getAllCategories() {
		ArrayList<Category> categories = new ArrayList<>();
		
		String sql = "SELECT * FROM `categories`";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setNote(rs.getString("note"));
				
				categories.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categories;
	}
	
	public static Category getCategoryById(int id) {
		Category category = new Category();
		
		String sql = "SELECT * FROM `categories` WHERE id = ?";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setInt(1, id);
			
			ResultSet rs = p.executeQuery();
			
			if (rs.next()) {
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setNote(rs.getString("note"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return category;
	}
	
	
	public static int getTotal() {
		String sql = "SELECT COUNT(*) as total FROM `categories`";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			
			ResultSet rs = p.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static ArrayList<Category> getAllCategories(int page, int perpage) {
		ArrayList<Category> categories = new ArrayList<>();
		
		String sql = "SELECT * FROM `categories` LIMIT ?,?";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setInt(1, (page-1) *perpage);
			p.setInt(2, perpage);
			
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setNote(rs.getString("note"));
				
				categories.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categories;
	}

	public static boolean insert(Category category) {
		String sql = "INSERT INTO `categories`(`name`, `note`) VALUES (?,?)";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setString(1, category.getName());
			p.setString(2, category.getNote());
			
			return p.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public static boolean update(Category category) {
		String sql = "UPDATE `categories` SET `name`= ?,`note`= ? WHERE id = ?";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setString(1, category.getName());
			p.setString(2, category.getNote());
			p.setInt(3, category.getId());
			
			return p.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
	public static boolean delete(int id) {
		String sql = "DELETE FROM `categories` WHERE id = ?";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setInt(1, id);
			
			return p.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
