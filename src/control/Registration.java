package control;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
/**
 * In this class the server will receive the attempt to registrate a user and will process it. 
 * 
 */
/**
 * @author Lucas
 *
 */
public class Registration extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	/**
	 * This doGet is "just in cause". Will not be used in this application. Call doPost.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * This doPost calls the servlet Users to registrate a new user. 
	 * @param request HttpServletRequest with information of request like username and password.
	 * @param response HttpServletResponse with information of where to go in case if the registration is successful or not
	 */
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Users us = new Users();

		user = new User(request.getParameter("email").toString(),
				request.getParameter("password").toString(), new Date(request.getParameter("dateOfBirth").toString()));

		if (us.createUser(user) == true) {

			response.sendRedirect("login.jsp");

		} else {

			response.sendRedirect("login.jsp");
		}

	}

}
