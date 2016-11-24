package service;

import java.util.List;
import dao.UserDao;
import dto.Order;
import dto.User;
import exception.BannedUserException;
import exception.InvalidPasswordException;
import exception.NotVerifiedUserException;

import org.apache.commons.mail.*;
import javax.mail.*;

//main functions on users,logical layer, uses the dao class.
public class UserService {

	public static List<User> getAllUsers() {
		return UserDao.getAllUsers();
	}

	public static User getUserByUsername(String username) {
		return UserDao.getUserByUsername(username);
	}

	@SuppressWarnings("deprecation")
	public static void signup(User user) {
		user.setPassword(encodePassword(user.getPassword()));
		UserDao.saveOrUpdate(user);
		
		Email email = new SimpleEmail();
		
		String myUserName = "zEMAIL";
		String Password = "zPASS";

		StringBuffer message = new StringBuffer("Dear " + user.getUsername() + "\n\n");
		

		email.setSubject("Verify Your Login Success! Account");
			
		message.append("You have successfuly set up your Login Success! account. If you set up this account please complete your registeration by clicking the link below.\n\n");
		message.append("http://localhost:8080/Assign2/verification?username=");
		message.append(user.getUsername());
		message.append("\n\n");;
		message.append("Have a nice day, \n\n From the team at Login Success!\n");

		try {
			email.setHostName("smtp.office365.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator(myUserName, Password));
			email.setSSLOnConnect(false);
			email.setTLS(true);
			email.setFrom(myUserName);
			email.setMsg(message.toString());
			email.addTo(user.getEmail());
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void confirm(User user, Order order) {
		Email email = new SimpleEmail();
		
		String myUserName = "zEMAIL";
		String Password = "zPASS";

		StringBuffer message = new StringBuffer("Dear " + user.getUsername() + "\n\n");
		String words = user.getHash();

		//confirmation of buying item
		email.setSubject("Order Confirmation");
		message.append("You have successfuly placed your order. Your order summary is shown below. To continue shopping or browsing click the link at the very bottom of the page.\n\n");
		message.append("http://localhost:8080/Assign2/signin");
		//message.append(words);
		message.append("\n\n");
		message.append("Have a nice day, \n\n From the team at Login Success!\n");

		try {
			email.setHostName("smtp.office365.com");
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator(myUserName, Password));
			email.setSSLOnConnect(false);
			email.setTLS(true);
			email.setFrom(myUserName);
			email.setMsg(message.toString());
			email.addTo(user.getEmail());
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public static String encodePassword(String password) {
		// TODO put more complex logic
		return "111" + password;
	}

	public static User signin(String username, String password)
			throws InvalidPasswordException, NotVerifiedUserException, BannedUserException {
		User user = UserDao.getUserByUsername(username);
		if (!user.getPassword().equals(encodePassword(password))) {
			throw new InvalidPasswordException();
		} else {
			if (!user.isVerified()) {
				throw new NotVerifiedUserException();
			}
			if (user.isBanned()) {
				throw new BannedUserException();
			}
			return user;
		}

	}
	
	public static String getUsernameById(int id) {
		return UserDao.getUserById(id).getUsername();
	}
	
	public static User getUserById(int id) {
		return UserDao.getUserById(id);
	}

}
