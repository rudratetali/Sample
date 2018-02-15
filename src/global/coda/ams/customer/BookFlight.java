package global.coda.ams.customer;

import java.io.IOException;

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
 * Servlet implementation class BookFlight
 */
@WebServlet("/Customer/BookFlight")
public class BookFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookFlight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int scheduleNo = new Integer(request.getParameter("ScheduleNo"));
		int seatCount = new Integer(request.getParameter("SeatCount"));
		String category = request.getParameter("Category");
		
			FlightBook flightObject = CustomerOperation.bookFlight(scheduleNo,category,seatCount);
			

		HttpSession user = request.getSession();
		user.setAttribute("flightSession", flightObject);
		RequestDispatcher rd = request.getRequestDispatcher("BookFlight.jsp");
		request.setAttribute("flightObject", flightObject);
		rd.include(request, response);

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
