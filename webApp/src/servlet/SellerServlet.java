package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;
import dto.*;

/**
 * Servlet implementation class SellerServlet
 */
@WebServlet("/seller")
public class SellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SellerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User curUser = (User) request.getSession().getAttribute("currentUser");
		
		if (curUser == null){
			response.sendRedirect(request.getContextPath() + "/signin");
			return;
		}
		
		ArrayList<OnSaleItem> onSaleItem = null;
		String action = request.getParameter("action");	
		
		request.setAttribute("curUser", curUser);
		
		if (action == null){
			onSaleItem = OnSaleItemService.getOnSaleItemByUserId(curUser.getId());
			request.setAttribute("onSaleItemList", onSaleItem);
			request.getRequestDispatcher("WEB-INF/pages/sellerHome.jsp").forward(request, response);
			return;
		}

		action.toLowerCase();
		
		if (action.equals("pause")){
			OnSaleItemService.pauseById(Integer.parseInt(request.getParameter("listingid")));
			onSaleItem = OnSaleItemService.getOnSaleItemByUserId(curUser.getId());
			request.setAttribute("onSaleItemList", onSaleItem);
			response.sendRedirect("seller");
			//request.getRequestDispatcher("WEB-INF/pages/sellerHome.jsp").forward(request, response);
			return;
		}
		
		else if (action.equals("unpause")){
			OnSaleItemService.unpauseById(Integer.parseInt(request.getParameter("listingid")));
			onSaleItem = OnSaleItemService.getOnSaleItemByUserId(curUser.getId());
			request.setAttribute("onSaleItemList", onSaleItem);
			response.sendRedirect("seller");
			//request.getRequestDispatcher("WEB-INF/pages/sellerHome.jsp").forward(request, response);
			return;
		}
		
		else if (action.equals("addListing")){
			response.sendRedirect("add_listing");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
