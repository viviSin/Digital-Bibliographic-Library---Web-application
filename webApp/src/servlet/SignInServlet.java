package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User;
import exception.BannedUserException;
import exception.InvalidPasswordException;
import exception.NotVerifiedUserException;
import service.UserService;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignInServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/signin.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String errorMsg = "";
		User user = null;
		try {
			user = UserService.signin(username, password);
		} catch (InvalidPasswordException e) {
			errorMsg = "Invalid Password.";
		} catch (NotVerifiedUserException e) {
			errorMsg = "Please verify your email address first.";
		} catch (BannedUserException e) {
			errorMsg = "Your account has been banned by admin.";
		}

		if (!errorMsg.isEmpty()) {
			response.sendRedirect("signin?errorMsg=" + errorMsg);
		} else {
			// do login
			request.getSession().setAttribute("currentUser", user);
			response.sendRedirect("items");
		}

	}

}
