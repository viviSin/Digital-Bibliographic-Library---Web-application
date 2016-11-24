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
import dao.*;
import service.*;

/**
 * Servlet implementation class AdminListingsServlet
 */
@WebServlet("/admin_history")
public class AdminHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminHistoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User curUser = UserService.getUserById(Integer.parseInt(request.getParameter("userid")));
		request.setAttribute("curUser", curUser); //note this is curUser we're checking not curUser logged in
		Admin a = (Admin) request.getSession().getAttribute("currentAdmin");
		
		if (a == null){
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		
		List<Order> buyHistory = AdminService.getBuyHistoryByUserId(curUser.getId());
		List<RemovedItem> removeHistory = AdminService.getRemovedHistoryByUserId(curUser.getId());
		
		request.setAttribute("buyHistory", buyHistory);
		request.setAttribute("removeHistory", removeHistory);
		
		
		
		
		request.getRequestDispatcher("WEB-INF/pages/adminHistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
