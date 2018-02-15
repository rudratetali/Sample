package global.coda.ams.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.coda.ams.beans.Flight;
import global.coda.ams.delegate.CustomerOperation;

/**
 * Servlet implementation class BookSearch
 */
@WebServlet("/Customer/BookSearch")
public class BookSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Flight> flightList = null;

		flightList = CustomerOperation.display();
		if (flightList != null) {
			RequestDispatcher rd = request.getRequestDispatcher("BookSearch.jsp");
			request.setAttribute("listFlight", flightList);
			rd.include(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("CustomerLoginMenu.jsp");
			request.setAttribute("error", "true");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seatCount = new Integer(request.getParameter("seatno"));
		String modify = request.getParameter("modify");
		String category = request.getParameter("class");
		boolean excessSeatCount = true;
		List<Flight> listFlight = modifySearch(request.getParameter("from"), request.getParameter("to"),
				request.getParameter("dateofJourney"), seatCount, request.getParameter("class"), modify);
		if(listFlight==null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("BookSearch.jsp");
			request.setAttribute("search", "false");
			rd.include(request, response);
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>The Available Flights are</h1>");
		out.println("<h2>Click on Flight number to View Details</h2>");
		out.println("<table border=3>");
		out.println("<tr>  <th>Flight Number</th> <th>Flight Name</th> <th>Time of depart</th> <th>Fare</th> </tr>");
		for (Flight flight : listFlight) {
			if (category.equals("E")) {
				if (seatCount > flight.getSeatsEconomy()) {

					continue;
				}
				excessSeatCount = false;
				out.println("<tr> <td> <a href=" + "http://localhost:8080/AirportWeb/BookFlight?ScheduleNo="
						+ flight.getScheduleNo() + "&SeatCount=" + seatCount + "&Category=" + category + ">"
						+ flight.getFlightNo() + "</a> </td>  <td> " + flight.getFlightName() + " </td> <td> "
						+ flight.getDepartureTime() + "</td> <td> " + flight.getEconomyFare() + "</td> </tr>");
			} else if (category.equals("B")) {
				if (seatCount > flight.getSeatsBusiness()) {
					continue;
				}
				excessSeatCount = false;
				out.println("<tr> <td> <a href=" + "http://localhost:8080/AirportWeb/BookFlight?ScheduleNo="
						+ flight.getScheduleNo() + "&SeatCount=" + seatCount + "&Category=" + category + ">"
						+ flight.getFlightNo() + "</a> </td>  <td> " + flight.getFlightName() + " </td> <td> "
						+ flight.getDepartureTime() + "</td> <td> " + flight.getEconomyFare() + "</td> </tr>");
			}
		}

		if (excessSeatCount) {
			RequestDispatcher rd = request.getRequestDispatcher("BookSearch.jsp");
			request.setAttribute("search", "false");
			rd.include(request, response);
		} else {

			RequestDispatcher rd = request.getRequestDispatcher("BookSearch.jsp");
			request.setAttribute("search", "true");
			rd.include(request, response);
		}

	}

	public static List<Flight> modifySearch(String from, String to, String date, int seatCount, String category,
			String modify) {
		List<Flight> flightList = null;
		switch (modify) {
		case "EarlyDeparture":
			flightList = CustomerOperation.search(from, to, date, seatCount, category, "departure");
			break;
		case "LateDeparture":
			flightList = CustomerOperation.search(from, to, date, seatCount, category, "departure desc");
			break;
		case "LowPrice":
			if (category.equals("E")) {
				flightList = CustomerOperation.search(from, to, date, seatCount, category,"economy_fare");
				break;
			} else {
				flightList = CustomerOperation.search(from, to, date, seatCount, category,"business_fare");
				break;
			}
		case "HighPrice":
			if (category.equals("E")) {
				flightList = CustomerOperation.search(from, to, date, seatCount, category,"economy_fare desc");
				break;
			} else {
				flightList = CustomerOperation.search(from, to, date, seatCount, category,"business_fare desc");
				break;
			}
		}
return flightList;
	}

}
