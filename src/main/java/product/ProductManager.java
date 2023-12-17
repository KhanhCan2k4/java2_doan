package product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import config.Config;
import core.Category;
import core.Product;
import dao.CategoryDAO;
import dao.ProductDAO;

/**
 * Servlet implementation class ProductManager
 */
public class ProductManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int page;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			// TODO: handle exception
			page = 1;
		}
		
		ArrayList<Product> products = ProductDAO.getAllProducts(page, Config.PER_PAGE_MANAGER);
		
		request.setAttribute("products", products);
		
		if(request.getAttribute("create_alert") != null) {
			request.setAttribute("create_alert", request.getAttribute("create_alert"));
		}
		if(request.getAttribute("update_alert") != null) {
			request.setAttribute("update_alert", request.getAttribute("update_alert"));
		}
		
		request.getRequestDispatcher("./admin/").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
