package global.coda.ams.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.coda.ams.beans.Customer;
import global.coda.ams.delegate.CustomerOperation;

/**
 * Servlet implementation class CustomerRegister
 */
@WebServlet("/Customer/CustomerRegister")
public class CustomerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Customer customer = new Customer();
		customer.setName(request.getParameter("name"));
		customer.setContact(request.getParameter("contact"));
		customer.setGender(request.getParameter("gender"));
		customer.setUserName(new Integer(request.getParameter("username")));
		customer.setPassword(request.getParameter("password"));

		if (!CustomerOperation.registerCustomer(customer)) {

			response.sendRedirect("CustomerLogin.jsp");

		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("CustomerRegister.jsp");
			request.setAttribute("error", "true");
			rd.include(request, response);
		}

		 
	}

	

}
