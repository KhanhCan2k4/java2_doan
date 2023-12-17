package category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import core.Category;
import dao.CategoryDAO;

/**
 * Servlet implementation class CreateCategory
 */
public class CreateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCategory() {
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
		String note = request.getParameter("note");
		
		if(name != null) {
			if(note == null) {
				note = "";
			}
			
			Category category = new Category(0, name, note);
			
			if(!CategoryDAO.insert(category)) {
				request.setAttribute("create_alert", "Error: Duplicate '" + name +"'");
			} else {
				request.setAttribute("create_alert", "Create '" + name +"' sucessfully");
			}
			
		} else {
			request.getRequestDispatcher("./admin/create_category.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("CategoryManager").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
