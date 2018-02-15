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

import global.coda.ams.beans.Flight;
import global.coda.ams.delegate.AdminOperation;

/**
 * Servlet implementation class DisplayUnAssigned
 */
@WebServlet("/Admin/DisplayUnAssigned")
public class DisplayUnAssigned extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayUnAssigned() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Flight> flightList=null;
		 try {
			flightList= AdminOperation.displayUnassignedFlights();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 if(flightList==null) {
			 RequestDispatcher rd = request.getRequestDispatcher("AssignFlights.jsp");
				request.setAttribute("noScheduled", "true");
				rd.include(request, response);
		 }
		 else
		 {
			 RequestDispatcher rd = request.getRequestDispatcher("DisplayUnAssigned.jsp");
				request.setAttribute("listFlight",flightList);
				rd.include(request, response);
		 }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	

}
