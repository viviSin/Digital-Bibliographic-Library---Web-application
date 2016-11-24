package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Admin;
import dto.User;
import service.AdminService;
import service.UserService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/pages/adminLogin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin admin = AdminService.getAdminByUsername(username);
		List<User> users = UserService.getAllUsers();
		request.setAttribute("users", users);
		
		if (!password.equals(admin.getPassword()) || admin == null) {
			String errorMsg = "Incorrect username or password";
			response.sendRedirect("admin?errorMsg=" + errorMsg);
			return;
		}

		//RequestDispatcher rd = request.getRequestDispatcher("/adminHome");
		//rd.forward(request, response);
		request.getSession().setAttribute("currentAdmin", admin);
		response.sendRedirect("admin_home");
	}

}
