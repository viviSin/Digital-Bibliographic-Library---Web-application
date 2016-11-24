package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import dao.ItemDao;
import dao.OnSaleItemDao;
import dao.ShoppingCartItemDao;
import dao.UserDao;
import dto.Item;
import dto.OnSaleItem;
import dto.ShoppingCartItem;
import dto.User;


@WebServlet("/onsaleitem")
public class OnSaleItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnSaleItemServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	User currentUser = (User) request.getSession().getAttribute("currentUser");
	//update the user, if user has been banned or something it will know.
	request.setAttribute("currentUser", UserDao.getUserById(currentUser.getId()));

	//add to cart
		if(request.getParameter("buttonName")!=null && request.getParameter("buttonName").equals("add to cart")){
		
			int onSaleItemId = Integer.parseInt(request.getParameter("id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int iid = Integer.parseInt((request.getParameter("iid")));
			Item item = ItemDao.getItemById(iid);
//			ShoppingCartItem scitem = new ShoppingCartItem(item,quantity, currentUser,onSaleItemId) ;
//			ShoppingCartItemDao.save(scitem);
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		OnSaleItem i = OnSaleItemDao.getOnSaleItemById(id);
		request.setAttribute("onSaleItem",i);
		request.getRequestDispatcher("WEB-INF/pages/onSaleItemPage.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
