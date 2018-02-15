package global.coda.ams.crew;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.ams.beans.FlightBook;
import global.coda.ams.delegate.CrewOperation;

/**
 * Servlet implementation class CrewSlot
 */
@WebServlet("/Crew/CrewSlot")
public class CrewSlot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrewSlot() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession crewUser = request.getSession();
		int crewId = (int) (crewUser.getAttribute("crewID"));
		String destination = request.getParameter("destination");
		String date = request.getParameter("dateofSlot");
		
		List<FlightBook> listFlight = null;
		try {
			listFlight = CrewOperation.slotRequest(crewId, destination, date);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (listFlight != null) {
			RequestDispatcher rd = request.getRequestDispatcher("crewSlot.jsp");
			request.setAttribute("listFlight", listFlight);
			rd.include(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("crewSlot.jsp");
			request.setAttribute("noFlight", "true");
			rd.include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int scheduleId = new Integer(request.getParameter("ScheduleNo"));
		HttpSession crewUser = request.getSession();
		int crewId = (int) (crewUser.getAttribute("crewID"));

		try {
			if (CrewOperation.slotRecord(scheduleId, crewId)) {
				RequestDispatcher rd = request.getRequestDispatcher("CrewLoginMenu.jsp");
				request.setAttribute("slotRequest", "false");
				rd.include(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("CrewLoginMenu.jsp");
				request.setAttribute("slotRequest", "true");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
