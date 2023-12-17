package product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import core.Category;
import core.Product;
import dao.CategoryDAO;
import dao.ProductDAO;

/**
 * Servlet implementation class CreateProduct
 */
@MultipartConfig
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String name = request.getParameter("name");
		
		if(name == null) {
			request.getRequestDispatcher("./admin/create_product.jsp").forward(request, response);
		}
		
		float price;
		try {
			price = Float.parseFloat(request.getParameter("price"));
		} catch (Exception e) {
			// TODO: handle exception
			price = 0.0f;
		}
		String detail = request.getParameter("detail");
		if(detail == null) {
			detail = "";
		}
		
		Part image = request.getPart("image");
		
		int categoryId;
		try {
			categoryId = Integer.parseInt(request.getParameter("category_id"));
		} catch (Exception e) {
			// TODO: handle exception
			categoryId = 0;
		}
		
		Product product = new Product(0, name, price, detail, image, categoryId);
		
		if(!ProductDAO.insert(product)) {
			request.setAttribute("create_alert", "Error: Duplicate '" + name +"'");
		} else {
			request.setAttribute("create_alert", "Create '" + name +"' sucessfully");
		}
		
		request.getRequestDispatcher("ProductManager").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
