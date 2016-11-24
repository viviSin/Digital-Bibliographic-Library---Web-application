package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.*;
import service.*;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin_home")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin a = (Admin) request.getSession().getAttribute("currentAdmin");
		
		if (a == null){
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
	
		List<User> allUser = UserService.getAllUsers();
		List<User> users = new ArrayList<User>();
		users = allUser;
		request.setAttribute("users", users);
		
		String action = request.getParameter("action");
		
		if (action == null) {
			request.getRequestDispatcher("WEB-INF/pages/adminHome.jsp").forward(request, response);
			return;
		}
		
		action.toLowerCase();
		
		if (action.equals("ban")) {
			int userid = Integer.parseInt(request.getParameter("userid"));
			AdminService.banUserById(userid);
			response.sendRedirect("admin_home");
			return;
		}
		else if (action.equals("unban")){
			int userid = Integer.parseInt(request.getParameter("userid"));
			AdminService.unbanUserById(userid);
			response.sendRedirect("admin_home");
			return;
		}
		
		
		request.getRequestDispatcher("WEB-INF/pages/adminHome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
