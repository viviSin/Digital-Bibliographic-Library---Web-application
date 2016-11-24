package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.OrderItemDao;
import dto.Order;
import dto.OrderItem;
import dto.ShoppingCartItem;
import dto.User;
import service.UserService;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(name = "OrderItemServlet", urlPatterns = { "/OrderItemServlet" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int orderId=-1;
//	TODO
	public int userId = 1;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("currentUser");
		System.out.println("u: " +u);
		if (u!= null)
			userId = u.getId();
		
		if (u!= null && u.isVerified()&& !u.isBanned()){
			if (request.getParameter("credit")!=null){
	//					TODO
				Order o =new Order();
				OrderDao odao =new OrderDao();
				o= odao.getOrderById(orderId);
	//			UserService.confirm(userId, o);
				System.out.println("send email");
				request.getRequestDispatcher("WEB-INF/pages/cart.jsp").forward(request, response);
			}else{
				int totalP=0;
				ArrayList<OrderItem> buyList = new ArrayList<OrderItem>();
				OrderItemDao odao =new OrderItemDao();
				buyList = odao.getUserOrderItems(userId);
				for (OrderItem k : buyList){
					totalP += (k.getPrice());
					orderId=k.getOrders_id();
				}
				
				request.setAttribute("price", totalP); // add to request
			    request.getSession().setAttribute("price", totalP); // add to session
			    this.getServletConfig().getServletContext().setAttribute("price", totalP); 
			    
				request.setAttribute("elements", buyList); // add to request
			    request.getSession().setAttribute("elements", buyList); // add to session
			    this.getServletConfig().getServletContext().setAttribute("elements", buyList); 
				
				response.getWriter().append("Served at: ").append(request.getContextPath());
				request.getRequestDispatcher("WEB-INF/pages/order.jsp").forward(request, response);
			}
		} else{
			 
			response.sendRedirect(request.getContextPath() + "/signin");
		}
	}

}
