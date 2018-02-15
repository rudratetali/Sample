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
 * Servlet implementation class SearchPNR
 */
@WebServlet("/Customer/SearchPNR")
public class SearchPNR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPNR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pnrId= new Integer(request.getParameter("pnrId"));

		HttpSession user = request.getSession();
		List<FlightBook> flightList=null;
		
			 flightList=CustomerOperation.SearchBookingPNR((int)user.getAttribute("userID"),pnrId);
		if(flightList!=null)
		{
		
		RequestDispatcher rd= request.getRequestDispatcher("SearchPNR.jsp");
		request.setAttribute("searchSuccess", "true");
		request.setAttribute("flightList", flightList);
		rd.include(request, response);}
		else
		{
			RequestDispatcher rd= request.getRequestDispatcher("SearchPNR.jsp");
			request.setAttribute("searchSuccess", "false");
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

