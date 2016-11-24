package servlet;


import java.sql.Timestamp;
import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OnSaleItemDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.ShoppingCartItemDao;
import dao.UserDao;
import dto.CartItem;
import dto.Item;
import dto.OnSaleItem;
import dto.OrderItem;
import dto.ShoppingCartItem;
import dto.User;
import service.OrderItemService;
import service.OrderService;
import service.UserService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	TODO
	int userId = -1; //hardCode
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			if (request.getParameter("orderYes")!=null){
				List<ShoppingCartItem> cItem = new ArrayList<ShoppingCartItem>();
				String[] ids = request.getParameterValues("delId");
				for (String s: ids){
					cItem.add(ShoppingCartItemDao.getItemByKey(Integer.parseInt(s)));
				}
				java.util.Date date= new java.util.Date();
				int orderId = OrderService.insert(userId,new Timestamp(date.getTime()));
				OnSaleItem a=null;
				OrderItem orderItem =new OrderItem();
				for (ShoppingCartItem k :cItem){
					User b=UserDao.getUserById(userId);
					orderItem.setUser(b);
					orderItem.setQuantity(k.getQuantity());
					orderItem.setItem(k.getItem());
					orderItem.setOrders_id(orderId);
					a = OnSaleItemDao.getOnSaleItemById(k.getOnSaleItem_id());
					orderItem.setSeller(a.getSeller());
					orderItem.setPrice((int) a.getPrice());
					OrderItemService.save(orderItem);
					ShoppingCartItemDao.delItemByKey(k.getKey());
				}
				response.sendRedirect(request.getContextPath() + "/OrderItemServlet");
				
			}else{
				if(request.getParameter("delYes")!=null && request.getParameter("delYes").equals("delYes")){
		    		String[] ids = request.getParameterValues("delId");
					for (String s: ids){
						ShoppingCartItemDao.delItemByKey(s);
					}
		    	}
		 
				
				List<ShoppingCartItem> cartItem = new ArrayList<ShoppingCartItem>();
				cartItem.clear();
				cartItem =  ShoppingCartItemDao.getSCItemByUserId(userId);
				List<OnSaleItem> onSaleList = OnSaleItemDao.getAllOnSaleItem();
				for(ShoppingCartItem k : cartItem){
					k.setAva(false);
				}
				for(OnSaleItem e : onSaleList){
					for(ShoppingCartItem k : cartItem){
						if (e.getId()==k.getOnSaleItem_id()){
							k.setAva(true);
						}
					}
				}
	//			System.out.println("cart size: " + cartItem.size());
				request.setAttribute("elements", cartItem); // add to request
	    	    request.getSession().setAttribute("elements", cartItem); // add to session
	    	    this.getServletConfig().getServletContext().setAttribute("elements", cartItem); // add to application context
	    	    
				request.getRequestDispatcher("WEB-INF/pages/cart.jsp").forward(request, response);
	
			}
		} else{
			 
			response.sendRedirect(request.getContextPath() + "/signin");
		}
		
	}

}
