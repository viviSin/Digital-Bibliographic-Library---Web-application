package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.User;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = -1; //hardCode
		User u = (User) request.getSession().getAttribute("currentUser");
//		System.out.println("u: " +u);
		if (u!= null)
			userId = u.getId();
		PrintWriter out = response.getWriter();
		if (u!= null && u.isVerified()&& !u.isBanned()){
			if (request.getParameter("updateInfo")!=null){
				if (!request.getParameter("username").isEmpty()){
					u.setUsername(request.getParameter("username"));
				}
				if (!request.getParameter("password").isEmpty()){
					u.setPassword(request.getParameter("password"));
				}
//				if (!request.getParameter("email").isEmpty()){
//					u.setEmail(request.getParameter("email"));
//				}
				if (!request.getParameter("yearOfBirth").isEmpty()){
					String a =request.getParameter("yearOfBirth");
					if (a.matches("\\d{4}")){
						if(Integer.parseInt(request.getParameter("yearOfBirth"))<2016)
							u.setYearOfBirth(Integer.parseInt(request.getParameter("yearOfBirth")));
					}
				}
				if (!request.getParameter("address").isEmpty()){
					u.setAddress(request.getParameter("address"));
				}
				if (!request.getParameter("creditcard").isEmpty()){
					u.setCreditcard(request.getParameter("creditcard"));
				}
				 UserDao.update(u);
			}
			
//			System.out.println("u: " +u.getEmail());
			ArrayList<User> uList =new ArrayList<User>();
			uList.clear();
			uList.add(u);
			request.setAttribute("elements", uList); // add to request
    	    request.getSession().setAttribute("elements", uList); // add to session
    	    this.getServletConfig().getServletContext().setAttribute("elements", uList); 
			request.getRequestDispatcher("WEB-INF/pages/userInfo.jsp").forward(request, response);
			
		} else{
			 
			response.sendRedirect(request.getContextPath() + "/signin");
		}
	}

}
