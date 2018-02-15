package global.coda.ams.crew;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.coda.ams.delegate.CrewOperation;

/**
 * Servlet implementation class crewLeave
 */
@WebServlet("/Crew/crewLeave")
public class crewLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int leaveCount;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public crewLeave() {
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
		if (operation.equals("Leave")) {
			HttpSession crewUser = request.getSession();
			int crewId = (int) (crewUser.getAttribute("crewID"));
			leaveCount = new Integer(request.getParameter("leaveCount"));
			String dateofLeave = request.getParameter("dateofLeave");
			if (CrewOperation.insertLeave(crewId, leaveCount, dateofLeave)) {
				RequestDispatcher rd = request.getRequestDispatcher("CrewLoginMenu.jsp");
				request.setAttribute("leaveRequest", "false");
				rd.include(request, response);
			}
			else
			{
			RequestDispatcher rd = request.getRequestDispatcher("CrewLoginMenu.jsp");
			request.setAttribute("leaveRequest", "true");
			rd.include(request, response);
			}
		}

		else if (operation.equals("Compensation")) {

			 leaveCount = new Integer(request.getParameter("leaveCount"));
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h2>Enter the Dates for Compensation</h2>");
			out.println("<form action=\"crewLeave\" method=\"post\">");
			for (int i = 1; i <= leaveCount; i++) {
				out.println("Enter the Date: <br>");
				out.println("<input type=\"date\" name=\"date" + i + "\" required/></br>");
			}
			out.println("<input type=\"submit\" name=\"compensate\" value=\"compensate\"/>");
			out.println("</form>");
			RequestDispatcher rd = request.getRequestDispatcher("crewLeave.jsp");
			request.setAttribute("reVisit", "false");
			rd.include(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession crewUser = request.getSession();
		int crewId = (int) (crewUser.getAttribute("crewID"));
		List<String> dateList = new ArrayList<String>();
		System.out.println(leaveCount);
		for (int i = 1; i <= leaveCount; i++) {

			String date = request.getParameter("date" + i);
			dateList.add(date);
			
		}
		
		
		if (CrewOperation.insertCompensate(crewId, leaveCount, dateList)) {
			RequestDispatcher rd = request.getRequestDispatcher("CrewLoginMenu.jsp");
			request.setAttribute("compRequest", "false");
			rd.include(request, response);
		}
		else {
		RequestDispatcher rd = request.getRequestDispatcher("CrewLoginMenu.jsp");
		request.setAttribute("compRequest", "true");
		rd.include(request, response);
		}
	}

	
}
