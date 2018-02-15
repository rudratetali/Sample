package global.coda.ams.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.ams.delegate.AdminOperation;

/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/Admin/AddFlight")
public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFlight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession adminUser = request.getSession();
		adminUser.setAttribute("flightId", new Integer(request.getParameter("FNo")));

		RequestDispatcher rd = request.getRequestDispatcher("AddFlight.jsp");
		rd.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession adminUser = request.getSession();
		int flightId = (int) adminUser.getAttribute("flightId");
		
		if (!AdminOperation.addFlight(flightId, request.getParameter("date"), request.getParameter("depttime"), request.getParameter("arrtime"), request.getParameter("Efare"),
				request.getParameter("Bfare"))) {
			RequestDispatcher rd = request.getRequestDispatcher("AdminLoginMenu.jsp");
			request.setAttribute("addedFlight", "true");
			rd.include(request, response);
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("AdminLoginMenu.jsp");
			request.setAttribute("addedFlight", "false");
			rd.include(request, response);
		}

	}

	
}
