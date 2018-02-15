package global.coda.ams.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.coda.ams.beans.CrewMembers;
import global.coda.ams.delegate.AdminOperation;

/**
 * Servlet implementation class ScheduleFlight
 */
@WebServlet("/Admin/ScheduleFlight")
public class ScheduleFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScheduleFlight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	static String date ;
	static int scheduleId;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 scheduleId = new Integer(request.getParameter("SNo"));
		List<CrewMembers> crewList= null;
		
			crewList=AdminOperation.displayCrew(scheduleId);
			if(crewList==null)
			{
				RequestDispatcher rd = request.getRequestDispatcher("ScheduleFlight.jsp");
				request.setAttribute("nocrew","true");
				rd.include(request, response);
			}
			else
			{
			RequestDispatcher rd = request.getRequestDispatcher("ScheduleFlight.jsp");
			request.setAttribute("listcrew",crewList);
			rd.include(request, response);
			}
		 
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] stringArray=request.getParameterValues("crewId");
		for(String string:stringArray)
		{
			
				if(AdminOperation.assignCrew(new Integer(string),scheduleId,date))
				{
					RequestDispatcher rd = request.getRequestDispatcher("AssignFlights");
					request.setAttribute("flightAssigned","false");
					rd.include(request, response);
				}
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("AdminLoginMenu.jsp");
		request.setAttribute("flightAssigned","true");
		rd.include(request, response);
		
	}

	

}
