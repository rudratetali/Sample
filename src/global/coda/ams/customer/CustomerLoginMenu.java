package global.coda.ams.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.ams.beans.FlightBook;
import global.coda.ams.delegate.CustomerOperation;

/**
 * Servlet implementation class CustomerMainMenu
 */
@WebServlet("/Customer/CustomerLoginMenu")
public class CustomerLoginMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerLoginMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("Book")) {
			response.sendRedirect("BookSearch");
		} 
		else if(operation.equalsIgnoreCase("Logout"))
		{
			HttpSession user= request.getSession();
			user.invalidate();
			response.sendRedirect("../MainMenu.jsp");

		}
		else if (operation.equalsIgnoreCase("View")) {

			HttpSession user = request.getSession();
			List<FlightBook> flightList=null;
			
				 flightList=CustomerOperation.viewBooking((int)user.getAttribute("userID"));
				 if(flightList!=null)
				 {
			
			user=request.getSession();
			user.setAttribute("flightList", flightList);
			RequestDispatcher rd= request.getRequestDispatcher("CustomerViewMenu.jsp");
			request.setAttribute("flightList", flightList);
			rd.include(request, response);

		}
				 else
				 {
					 RequestDispatcher rd= request.getRequestDispatcher("CustomerLoginMenu.jsp");
						request.setAttribute("noFlight", "true");
						rd.include(request, response);
				 }
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
