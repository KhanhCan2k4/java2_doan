package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import core.Product;

public class ProductDAO {
	
	public static void main(String[] args) {
		System.out.println(getAllProducts(2, 1).size());
	}
	
	public static ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
		
		String sql = "SELECT * FROM `products`";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setDetail(rs.getString("detail"));
				product.setImage(rs.getBytes("image"));
				product.setCategory_id(rs.getInt("category_id"));
				
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
		
	}

	public static ArrayList<Product> getAllProducts(int page, int perpage) {
		ArrayList<Product> products = new ArrayList<>();
		
		String sql = "SELECT * FROM `products` LIMIT ?,?";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setInt(1, (page-1) *perpage);
			p.setInt(2, perpage);
			
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setDetail(rs.getString("detail"));
				product.setImage(rs.getBytes("image"));
				product.setCategory_id(rs.getInt("category_id"));
				
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
		
	}
	
	public static Product getProductById(int id) {
		Product product = new Product();
		
		String sql = "SELECT * FROM `products` WHERE id = ?";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setInt(1, id);
			
			ResultSet rs = p.executeQuery();
			
			if (rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setDetail(rs.getString("detail"));
				product.setImage(rs.getBytes("image"));
				product.setCategory_id(rs.getInt("category_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return product;
		
	}

	public static ArrayList<Product> getProductsByCategoryId(int id, int page, int perpage) {
		ArrayList<Product> products = new ArrayList<>();
		
		String sql = "SELECT * FROM (SELECT * FROM `products` LIMIT ?, ?) as mytable WHERE mytable.category_id = ? OR ? = 0";
		
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setInt(1, (page -1)*perpage);
			p.setInt(2, perpage);
			p.setInt(3, id);
			p.setInt(4, id);
			
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setDetail(rs.getString("detail"));
				product.setImage(rs.getBytes("image"));
				product.setCategory_id(rs.getInt("category_id"));
				
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
		
	}

	public static int getTotal() {
		String sql = "SELECT COUNT(*) as total FROM `products`";
		
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

	public static int getTotalByCategoryId(int id) {
		String sql = "SELECT COUNT(*) as total FROM `products` WHERE category_id = ? OR ? = 0";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setInt(1, id);
			p.setInt(2, id);
			
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

	public static int getTotalBySearch(String key) {
		String sql = "SELECT COUNT(*) as total FROM `products` WHERE name LIKE CONCAT ('%', ? , '%' ) OR detail LIKE CONCAT ('%', ? , '%' )";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setString(1, key);
			p.setString(2, key);
			
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

	public static boolean insert(Product product) {
		String sql = "INSERT INTO `products`(`name`, `price`, `detail`, `image`, `category_id`) VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setString(1, product.getName());
			p.setFloat(2, product.getPrice());
			p.setString(3, product.getDetail());
			FileInputStream fis;
			try {
				fis = (FileInputStream) product.getImageFile().getInputStream();
				
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
			
			p.setBinaryStream(4, (InputStream)fis);
			p.setInt(5, product.getCategory_id());
			
			return p.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean update(Product product) {
		String sql = "UPDATE `products` SET `name`=?,`price`=?,`detail`=?,`category_id`=? WHERE id = ?";
		
		Boolean flag = true;
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setString(1, product.getName());
			p.setFloat(2, product.getPrice());
			p.setString(3, product.getDetail());
			p.setInt(4, product.getCategory_id());
			p.setInt(5, product.getId());
			
			flag = p.executeUpdate() > 0;
			
			if(product.getImageFile().getSize() > 0) {
				System.out.println("have file");
				sql = "UPDATE `products` SET image= ? WHERE id = ?";
				p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
				FileInputStream fis = (FileInputStream) product.getImageFile().getInputStream();
				p.setBinaryStream(1, (InputStream)fis);
				p.setInt(2, product.getId());
				
				System.out.println( p.executeUpdate());
				
			}
			
			
			return flag;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public static boolean delete(int id) {
		String sql = "DELETE FROM `products` WHERE id = ?";
		
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
	
	public static ArrayList<Product> search(String key, int page, int perpage) {
		ArrayList<Product> products = new ArrayList<>();
		
		String sql = "SELECT * FROM `products` WHERE name LIKE CONCAT ('%', ? , '%' ) OR detail LIKE CONCAT ('%', ? , '%' ) LIMIT ?, ?";
		
		try {
			PreparedStatement p = (PreparedStatement) DatabaseUtil.getConnection().prepareStatement(sql);
			p.setString(1, key);
			p.setString(2, key);
			p.setInt(3, (page -1) *perpage);
			p.setInt(4, perpage);
			
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				product.setDetail(rs.getString("detail"));
				product.setImage(rs.getBytes("image"));
				product.setCategory_id(rs.getInt("category_id"));
				
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
}
