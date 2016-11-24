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
@WebServlet("/admin_listings")
public class AdminListingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminListingsServlet() {
		super();
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
		
		String action = request.getParameter("action");	
		User curUser = UserService.getUserById(Integer.parseInt(request.getParameter("userid")));
		request.setAttribute("curUser", curUser);
		
		List<OnSaleItem> onSaleItems = null;
		
		if (action == null) {	
			onSaleItems = OnSaleItemService.getOnSaleItemByUserId(curUser.getId());
			request.setAttribute("listings", onSaleItems);
			request.getRequestDispatcher("WEB-INF/pages/adminListings.jsp").forward(request, response);
			return;
		}

		action.toLowerCase();
		
		if (action.equals("remove")) {
			OnSaleItemService.deleteById(Integer.parseInt(request.getParameter("listingid")));
			onSaleItems = OnSaleItemService.getOnSaleItemByUserId(curUser.getId());
			request.setAttribute("listings", onSaleItems);	
		}
		

		request.getRequestDispatcher("WEB-INF/pages/adminListings.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
