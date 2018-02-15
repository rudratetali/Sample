package global.coda.ams.admin;

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

import global.coda.ams.beans.Flight;
import global.coda.ams.delegate.AdminOperation;

/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/Admin/DisplayFlight")
public class DisplayFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Flight> listFlight =null;
		try {
			 listFlight =AdminOperation.displayFlights();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(listFlight!=null)
		{
			for(Flight flight:listFlight)
			{
				System.out.println(flight.getFlightId());
			}
		RequestDispatcher rd = request.getRequestDispatcher("DisplayFlight.jsp");
		request.setAttribute("flightList", listFlight);
		rd.include(request, response);
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("DisplayFlight.jsp");
			request.setAttribute("noFlight", "true");
			rd.include(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession adminUser = request.getSession();
		adminUser.setAttribute("flightId", new Integer(request.getParameter("FNo")));

		RequestDispatcher rd = request.getRequestDispatcher("AddFlight.jsp");
		rd.include(request, response);
	}
}
	

