package global.coda.ams.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.ams.beans.Customer;
import global.coda.ams.delegate.CustomerOperation;

/**
 * Servlet implementation class CustomerLogin
 */
@WebServlet("/Customer/CustomerLogin")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		
		if (operation.equals("Register")) {
			response.sendRedirect("CustomerRegister.jsp");
		} else if(operation.equals("Login")) {
			int userId = new Integer(request.getParameter("name"));
			String password = request.getParameter("password");
			Customer customer = CustomerOperation.customerLogin(userId, password);
			if (customer != null) {
				HttpSession user = request.getSession(true);
				user.setAttribute("userName", customer.getName());
				user.setAttribute("userID", customer.getUserName());
				response.sendRedirect("CustomerLoginMenu.jsp");

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("CustomerLogin.jsp");
				request.setAttribute("userValied", "false");
				rd.include(request, response);

			}

		}
	}

}
