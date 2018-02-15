package global.coda.ams.crew;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CrewLoginMenu
 */
@WebServlet("/Crew/CrewLoginMenu")
public class CrewLoginMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrewLoginMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation= request.getParameter("operation");
		switch(operation)
		{
		case "View Assigned":
			response.sendRedirect("ViewCrewAssigned");
			break;
		case "Leave Request":
			response.sendRedirect("crewLeave.jsp");
			break;
		case "View Profile":
			response.sendRedirect("ViewCrewProfile");
			break;
		case "Slot Request":
			response.sendRedirect("crewSlot.jsp");
			break;	
		case "Logout":
			HttpSession crewUser= request.getSession();
			crewUser.invalidate();
			response.sendRedirect("../MainMenu.jsp");
			break;
			
		}
	}

}
