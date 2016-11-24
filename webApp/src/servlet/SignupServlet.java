package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import dto.User;
import service.UserService;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String email = request.getParameter("email");

		if (!password.equals(password2)) {
			String errorMsg = "Two password are not identical";
			response.sendRedirect("signup?errorMsg=" + errorMsg);
			return;
		}

		// check username exists
		User existUser = UserService.getUserByUsername(username);
		if (existUser != null) {
			String errorMsg = "Existing username.";
			response.sendRedirect("signup?errorMsg=" + errorMsg);
			return;
		}
		
		// check if the format of the email is correct
		EmailValidator valid = EmailValidator.getInstance();
		if (valid.isValid(email) == false) {
			String errorMsg = "Invalid Email Address.";
			response.sendRedirect("signup?errorMsg=" + errorMsg);
			return;
		}

		// signup
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		UserService.signup(user);
		response.sendRedirect("resp?successMsg=" + "You have sign up sucessfully.");

	}

}